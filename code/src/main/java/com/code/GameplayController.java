package com.code;

import com.code.utils.AlertUtils;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * FXML Controller class
 *
 * @author aluno
 */
public class GameplayController implements Initializable {

    /**
     * Initializes the controller class.
     */
   /** @FXML
    public Label text;
    */

    @FXML
    public AnchorPane inicial;

    /**@FXML
    public Rectangle villain;
    
    public ArrayList<Circle> balas = new ArrayList<>();
    */

    //Declarando o canvas
    private Canvas canvas;
    private GraphicsContext gc;

    //Tamanhos do tile do tile set
    private static final int TILE_SIZE = 16;
    private static final int TILE_SCALE = 2;

    //Tamanho do sprite do bonequinho, colunas e numero de vetor para percorrer
    private static final int FRAME_W = 32;
    private static final int FRAME_H = 32;
    private static final int SPRITE_COLS = 5;
    private static final int FRAMES_POR_DIR = 6;

    private static final int DIR_DOWN = 0;
    private static final int DIR_UP = 1;
    private static final int DIR_LEFT = 2;
    private static final int DIR_RIGHT = 3;

    // Configurações para ler o mapa
    private Image tileset;
    private Image spriteSheet;
    private List<int[]> allLayers = new ArrayList<>(); // layers visuais da pasta "Visual"
    private int[] collisionData;                       // layer da pasta "Colisores"
    private int mapCols;
    private int mapRows;
    private int tilesetCols; //Quantas linhas tem na imagem dos tiles do mapa

    //Animação
    private int currentDirection = DIR_DOWN; //Lembra que declarei dir_down como 0? é para ficar um idle mais intuitivo
    private int currentFrame     = 0;
    private long lastFrameTime   = 0; //guarda o momento no tempo que a animação está
    private static final long FRAME_DURATION_NS = 100_000_000L; //é em nanosegundos então isto é 0,1 segundos

    private boolean movingUp, movingDown, movingLeft, movingRight; //onde o player ta clicando, basicamente
    private double playerX = 100;
    private double playerY = 100;
    private static final double SPEED = 2.0;

    // Lista de portas e objetos de boss lidos do JSON
    private List<Porta> portas = new ArrayList<>();

    /**@Override
    public void initialize(URL url, ResourceBundle rb) {
        inicial.sceneProperty().addListener((obs,oldScene,newScene)->{
            if(newScene != null){
                System.out.println(player.getLayoutY());
                newScene.setOnKeyPressed((e)->{
                    if(e.getCode()==KeyCode.W && player.getLayoutY()>= 0){
                        player.setLayoutY(player.getLayoutY()-20);
                    }
                    if(e.getCode()==KeyCode.S && player.getLayoutY()+20<=400){
                        player.setLayoutY(player.getLayoutY()+20);
                    }
                    if(e.getCode()==KeyCode.A && player.getLayoutX()>=0){
                        player.setLayoutX(player.getLayoutX()-20);
                    }
                     if(e.getCode()==KeyCode.D && player.getLayoutX()+20< 600){
                        player.setLayoutX(player.getLayoutX()+20);
                    }
                });
            }
        });
    }   
}
*/

    //Separei as ações do override antigo em métodos
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarCanvas();
        carregarAssets();
        configurarControles();
        iniciarGameLoop();
    }

    private void configurarCanvas() {
        canvas = new Canvas(600, 400); // do tamanho do anchor pane
        gc = canvas.getGraphicsContext2D();
        gc.setImageSmoothing(false); //Não borrar as sprites
        inicial.getChildren().add(canvas); //Coloca o canvas no pane assim que a gameplay abre
    }

    private void carregarAssets() {
        tileset = new Image(getClass().getResourceAsStream("/com/code/assets/tileset1.png")); //basicamente pegar as cores do mapa -tiles- para pintar no canvas
        tilesetCols = (int) tileset.getWidth() / TILE_SIZE;

        spriteSheet = new Image(getClass().getResourceAsStream("/com/code/assets/character.png")); //Mesma coisa de cima, mas do personagem

        //Leitor padrão de json com nossas variaveis
        try {
            InputStream is = getClass().getResourceAsStream("/com/code/assets/" + App.mapaAtual);
            JSONParser parser = new JSONParser();
            JSONObject mapa = (JSONObject) parser.parse(new InputStreamReader(is));

            mapCols = ((Long) mapa.get("width")).intValue();
            mapRows = ((Long) mapa.get("height")).intValue();

            JSONArray layers = (JSONArray) mapa.get("layers");

            for (Object layerObj : layers) {
                JSONObject camada = (JSONObject) layerObj;
                String tipo = (String) camada.get("type");
                String nome = (String) camada.get("name");

                if ("group".equals(tipo)) {
                    JSONArray subLayers = (JSONArray) camada.get("layers");

                    if ("Visual".equals(nome)) {
                        // Lê todos os layers de tile dentro da pasta Visual
                        for (Object subObj : subLayers) {
                            JSONObject sub = (JSONObject) subObj;
                            JSONArray data = (JSONArray) sub.get("data");
                            int[] tileData = new int[data.size()];
                            for (int i = 0; i < data.size(); i++) {
                                tileData[i] = ((Long) data.get(i)).intValue();
                            }
                            allLayers.add(tileData);
                        }

                    } else if ("Colisores".equals(nome)) {
                        // Lê o layer de colisão dentro da pasta Colisores
                        JSONObject sub = (JSONObject) subLayers.get(0);
                        JSONArray data = (JSONArray) sub.get("data");
                        collisionData = new int[data.size()];
                        for (int i = 0; i < data.size(); i++) {
                            collisionData[i] = ((Long) data.get(i)).intValue();
                        }
                    }

                } else if ("objectgroup".equals(tipo)) {
                    // Lê todos os objetos com "destination" (Portas e Bosses)
                    JSONArray objects = (JSONArray) camada.get("objects");
                    if (objects == null) continue;

                    for (Object objRaw : objects) {
                        JSONObject obj = (JSONObject) objRaw;

                        String dest = null;
                        JSONArray props = (JSONArray) obj.get("properties");
                        if (props != null) {
                            for (Object p : props) {
                                JSONObject prop = (JSONObject) p;
                                if ("destination".equals(prop.get("name"))) {
                                    dest = (String) prop.get("value");
                                }
                            }
                        }

                        if (dest != null) {
                            double ox = ((Number) obj.get("x")).doubleValue();
                            double oy = ((Number) obj.get("y")).doubleValue();
                            double ow = ((Number) obj.get("width")).doubleValue();
                            double oh = ((Number) obj.get("height")).doubleValue();
                            portas.add(new Porta(ox, oy, ow, oh, dest));
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Erro ao carregar mapa: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //Para o controle funcionar aplicavelmente, ent eu usei de base seu código que a lógica tava perfeita e "merge"
    private void configurarControles() {
        inicial.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene == null) return;

            //Botão apertado, a logica é converter o keycode para boolean comparando
            newScene.setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.W) { movingUp = true;    currentDirection = DIR_UP; }
                if (e.getCode() == KeyCode.S) { movingDown = true;  currentDirection = DIR_DOWN; }
                if (e.getCode() == KeyCode.A) { movingLeft = true;  currentDirection = DIR_LEFT; }
                if (e.getCode() == KeyCode.D) { movingRight = true; currentDirection = DIR_RIGHT; }

                if (e.getCode() == KeyCode.SPACE) {
                    for (Porta p : portas) {
                        if (colideCom(p)) {
                            try {
                                if (p.destination.endsWith(".json")) {
                                    // é um mapa, recarrega a mesma scene com novo JSON
                                    App.mapaAtual = p.destination;
                                    App.setRoot("gameplay");
                                } else {
                                    // é uma scene de boss
                                    App.setRoot(p.destination);
                                }
                            } catch (Exception err) {
                                AlertUtils.error("Erro", err);
                            }
                            break;
                        }
                    }
                }
            });

            //Quando parar de apertar o botão
            newScene.setOnKeyReleased(e -> {
                if (e.getCode() == KeyCode.W) { movingUp = false;    currentDirection = DIR_UP; }
                if (e.getCode() == KeyCode.S) { movingDown = false;  currentDirection = DIR_DOWN; }
                if (e.getCode() == KeyCode.A) { movingLeft = false;  currentDirection = DIR_LEFT; }
                if (e.getCode() == KeyCode.D) { movingRight = false; currentDirection = DIR_RIGHT; }
            });
        });
    }

    private boolean colideCom(Porta p) {
        return playerX < p.x + p.w &&
               playerX + FRAME_W > p.x &&
               playerY < p.y + p.h &&
               playerY + FRAME_H > p.y;
    }

    // Verifica se a posição tem um tile colisor
    private boolean temColisor(double x, double y) {
        int col = (int)(x / (TILE_SIZE * TILE_SCALE));
        int row = (int)(y / (TILE_SIZE * TILE_SCALE));
        if (col < 0 || row < 0 || col >= mapCols || row >= mapRows) return true;
        if (collisionData == null) return false;
        int index = row * mapCols + col;
        return collisionData[index] != 0;
    }

    //Aplicação da biblioteca pro loop de animação do personagem
    private void iniciarGameLoop() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                atualizar(now);
                renderizar();
            }
        };
        timer.start();
    }

    //Esse aqui é para desenhar o personagem parado quando a lógica avisar que não tem nenhum botão pressionado.
    private void atualizar(long now) {
        boolean moving = false;

        if (movingUp    && !temColisor(playerX, playerY - SPEED))           { playerY -= SPEED; moving = true; }
        if (movingDown  && !temColisor(playerX, playerY + FRAME_H + SPEED)) { playerY += SPEED; moving = true; }
        if (movingLeft  && !temColisor(playerX - SPEED, playerY))           { playerX -= SPEED; moving = true; }
        if (movingRight && !temColisor(playerX + FRAME_W + SPEED, playerY)) { playerX += SPEED; moving = true; }

        if (moving && now - lastFrameTime > FRAME_DURATION_NS) {
            currentFrame  = (currentFrame + 1) % FRAMES_POR_DIR;
            lastFrameTime = now;
        } else if (!moving) {
            currentFrame = 0;
        }
    }

    private void renderizar() {
        gc.clearRect(0, 0, 600, 400);

        // Renderiza todos os layers visuais na ordem
        for (int[] tileLayer : allLayers) {
            for (int i = 0; i < tileLayer.length; i++) {
                int gid = tileLayer[i];
                if (gid == 0) continue;

                int tileIndex = gid - 1;
                int tileCol   = tileIndex % tilesetCols;
                int tileRow   = tileIndex / tilesetCols;

                double srcX  = tileCol * TILE_SIZE;
                double srcY  = tileRow * TILE_SIZE;
                double destX = (i % mapCols) * TILE_SIZE * TILE_SCALE;
                double destY = (i / mapCols) * TILE_SIZE * TILE_SCALE;

                gc.drawImage(tileset, srcX, srcY, TILE_SIZE, TILE_SIZE,
                                      destX, destY, TILE_SIZE * TILE_SCALE, TILE_SIZE * TILE_SCALE);
            }
        }

        int frameAbsoluto = currentDirection * FRAMES_POR_DIR + currentFrame;
        int frameCol      = frameAbsoluto % SPRITE_COLS;
        int frameRow      = frameAbsoluto / SPRITE_COLS;

        double spriteSrcX = frameCol * FRAME_W;
        double spriteSrcY = frameRow * FRAME_H;

        gc.drawImage(spriteSheet, spriteSrcX, spriteSrcY, FRAME_W, FRAME_H,
                                  playerX, playerY, FRAME_W, FRAME_H);
    }

    private static class Porta {
        double x, y, w, h;
        String destination;

        Porta(double x, double y, double w, double h, String destination) {
            this.x = x; this.y = y; this.w = w; this.h = h;
            this.destination = destination;
        }
    }
}