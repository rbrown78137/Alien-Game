package world;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import characters.Character;
import characters.Hero;
import characters.MoverEnemy;
import characters.TankEnemy;
import events.AddSpawnEventsEvent;
import events.WorldEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.EnemyAttackHandler;
import logic.MouseHandler;
import logic.WeaponHandler;
import objects.AmmoCounter;
import objects.EndGameScreen;
import objects.HealthCounter;
import objects.MoneyCounter;
import objects.PersistantObject;
import objects.PregameScreen;
import objects.TraderMenu;
import objects.WaveCounter;
import objects.WeaponDisplay;
import logic.MouseInfo;
import logic.TraderMouseClickHandler;

public class World {
	ArrayList<String> keyInput;
	ArrayList<MouseInfo> mouseInput;
	public Canvas canvas;
	public GraphicsContext space;
	public List<Character> characters;
	public List<PersistantObject> persistantObjects;
	public List<WorldEvent> futureEvents;
 	public long startTime;
	public Hero hero;
	public long lastTime;
	public int gameState;
	public static final int PREGAME_SCREEN = 0;
	public static final int IN_ROUND = 1;
	public static final int IN_TRADE_MENU=2;
	public static final int END_GAME_SCREEN = 3;
	public static final int TRANSITIONING_TO_ROUND =4;
	public int round;
	public WaveCounter waveCounter;
	public MoneyCounter moneyCounter;
	public WeaponDisplay weaponDisplay;
	public AmmoCounter ammoCounter;
	public TraderMenu traderMenu;
	public HealthCounter healthCounter;
	public EnemyAttackHandler enemyAttackHandler;
	public EndGameScreen endGameScreen;
	public PregameScreen pregameScreen;
	public long endTime;
	public World(){
		gameState=PREGAME_SCREEN;
		round =0;
		mouseInput = new ArrayList<MouseInfo>();
		keyInput = new ArrayList<String>();
		canvas = new Canvas(800, 800);
		space = canvas.getGraphicsContext2D();
		persistantObjects = new ArrayList<PersistantObject>();
		MouseHandler.world =this;
		WorldEvent.world = this;
		TraderMouseClickHandler.world =this;
		futureEvents = new CopyOnWriteArrayList<WorldEvent>();;
		characters = new ArrayList<Character>();
		hero = new Hero("Hero/Mainc");
		waveCounter=new WaveCounter();
		moneyCounter=new MoneyCounter();
		weaponDisplay=new WeaponDisplay();
		ammoCounter = new AmmoCounter();
		traderMenu = new TraderMenu();
		healthCounter = new HealthCounter(hero);
		WeaponHandler.weaponDisplay= this.weaponDisplay;
		startTime = System.currentTimeMillis();
		lastTime = startTime;
		pregameScreen = new PregameScreen();
		persistantObjects.add(pregameScreen);
		enemyAttackHandler = new EnemyAttackHandler(this);
		endGameScreen = new EndGameScreen();
		endTime =0;
	}
	public void update() {
		long currentTime = System.currentTimeMillis();
		long timePassed = currentTime -lastTime;
		for(WorldEvent event: futureEvents) {
			if(event.remove(currentTime)) {
				futureEvents.remove(event);
			}
		}
		
		/*for(MouseInfo info: mouseInput) {
			System.out.println("Event: "+ "X: "+ info.x+ " Y: "+info.y+" Time: " + info.time + " Clicked: "+info.down);
		}
		System.out.println("----------");
		*/
		MouseHandler.handleMouseClick(mouseInput);
		enemyAttackHandler.handleAttack();
		space.clearRect(0, 0, 800,800);
		if(gameState==IN_ROUND) {
		hero.move(timePassed,keyInput);
		hero.draw(space);
		//radius lines must be drawn first so they appear below characters
		for(Character character:characters) {
			if(character instanceof TankEnemy) {
				int indicatorRadius = 60;
				if(((TankEnemy)character).tankState == TankEnemy.ANGERED_STATE) {
					space.setFill(Color.YELLOW);
					space.fillOval(character.x-indicatorRadius, character.y+10-indicatorRadius, 2*indicatorRadius, 2*indicatorRadius);
				}else if(((TankEnemy)character).tankState == TankEnemy.CHARGE_STATE) {
					space.setFill(Color.ORANGE);
					space.fillOval(character.x -indicatorRadius, character.y+10-indicatorRadius, 2*indicatorRadius, 2*indicatorRadius);
				}else if(((TankEnemy)character).tankState == TankEnemy.ANGRY_RUN_STATE) {
					space.setFill(Color.RED);
					space.fillOval(character.x-indicatorRadius, character.y+10-indicatorRadius, 2*indicatorRadius, 2*indicatorRadius);
				}
				
				
			}
			if(character instanceof MoverEnemy && ((MoverEnemy)character).charging) {
				space.setStroke(Color.BLACK);
				space.setLineWidth(2);
				space.strokeOval(character.x-MoverEnemy.EXPLOSION_RADIUS, character.y-MoverEnemy.EXPLOSION_RADIUS,2*MoverEnemy.EXPLOSION_RADIUS , 2*MoverEnemy.EXPLOSION_RADIUS);
			}
		}
		for(Character character:characters) {
			character.action();
			character.move(timePassed,hero);
			character.draw(space);
		}
		}
		for(PersistantObject object:persistantObjects) {
			object.drawObject(space);
		}
		lastTime = currentTime;
		if(gameState==TRANSITIONING_TO_ROUND) {
			if(persistantObjects.contains(traderMenu)) {
				persistantObjects.remove(traderMenu);
			}
			hero.health=5;
			gameState=IN_ROUND;
			round++;
			waveCounter.wave++;
			futureEvents.add(new AddSpawnEventsEvent(round));
		}
		if(gameState==IN_ROUND && characters.isEmpty() && futureEvents.isEmpty()) {
			gameState=IN_TRADE_MENU;
			persistantObjects.add(traderMenu);
		}
		if(gameState==IN_ROUND && hero.health<=0) {
			gameState=END_GAME_SCREEN;
			endGameScreen.round = round;
			persistantObjects.clear();
			persistantObjects.add(endGameScreen);
			endTime = System.currentTimeMillis();
			mouseInput.clear();
		}
	}
	public void setListeners(Scene primaryScene){
		primaryScene.setOnKeyPressed((pressEvent) -> {
			if(!keyInput.contains(pressEvent.getText())) {
			keyInput.add(pressEvent.getText());
			WeaponHandler.switchWeapon(pressEvent.getText());
			}
		});
		primaryScene.setOnKeyReleased((releaseEvent) -> {
			keyInput.remove(releaseEvent.getText());
		});
		primaryScene.setOnMousePressed((mousePress)->{
			mouseInput.add(new MouseInfo(mousePress.getSceneX(),mousePress.getSceneY(),System.currentTimeMillis(),true));
		});
		primaryScene.setOnMouseReleased(mousePress->{
			if(mouseInput.size()>0) {
			mouseInput.get(mouseInput.size()-1).down = false;
			}
		});
		primaryScene.setOnMouseDragged((mouseDragged)->{
			if(mouseInput.size()>0) {
			mouseInput.get(mouseInput.size()-1).x = mouseDragged.getSceneX();
			mouseInput.get(mouseInput.size()-1).y = mouseDragged.getSceneY();
			}
		});
	}
}

