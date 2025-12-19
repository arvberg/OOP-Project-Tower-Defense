    package com.IONA.TowerDefense.model.units.enemies;

    import com.IONA.TowerDefense.HeartBeat;
    import com.IONA.TowerDefense.model.Direction;
    import com.IONA.TowerDefense.model.ui.HealthBar;
    import com.IONA.TowerDefense.model.units.Unit;
    import com.IONA.TowerDefense.model.units.interfaces.EnemyDeathListener;
    import com.badlogic.gdx.graphics.Texture;
    import com.badlogic.gdx.math.Vector2;
    import com.badlogic.gdx.math.Rectangle;

    import javax.swing.*;

    import java.util.ArrayList;
    import java.util.List;

    import static com.IONA.TowerDefense.HeartBeat.delta;
    import static java.lang.Math.abs;

    public abstract class Enemy extends Unit {
        protected HealthBar healthBar;
        protected Direction dir;
        protected int hp;
        protected int maxHp;
        public float speed;
        protected int money;
        protected Rectangle hitBox;
        protected float width;
        protected float height;
        protected int segmentIndex = 0;
        protected int damage;
        protected int difficulty;

        public Enemy(int difficulty){
            this.difficulty = difficulty;
        }
        public Rectangle getHitBox() {
            return hitBox;
        }


        public void move(float delta) {

             // sekunder per frame
             // speed = units per sekund
            switch (dir) {
                case NORTH -> position.y += speed*delta;
                case SOUTH -> position.y -= speed*delta;
                case EAST -> position.x += speed*delta;
                case WEST -> position.x -= speed*delta;
            }
            setHitBox(width, height);

            //Move HealthBar along with Enemy
            if (healthBar.getPosition() != null){
                healthBar.setPosition(position.x, position.y);
            }
        }

        public void setDirection(Direction direction) {
            this.dir = direction;
        }

        public float getDegreeDirection() {
            switch (dir) {
                case WEST:  return 0f;
                case NORTH: return 270f;
                case EAST:  return 180f;
                case SOUTH: return 90f;
                default:    return 0f; // säker fallback
                }
        }


        public boolean outsideSegment(Vector2 enemyPosition, Vector2 segmentEnd, Direction direction) {

            return switch (direction) {
                case NORTH -> enemyPosition.y >= segmentEnd.y;
                case SOUTH -> enemyPosition.y <= segmentEnd.y;
                case EAST -> enemyPosition.x >= segmentEnd.x;
                case WEST -> enemyPosition.x <= segmentEnd.x;
            };
        }

        public void setHitBox(float width, float height) {

            float newX = (position.x - width/2);
            float newY = (position.y - height/2);


            if (hitBox == null) {
                hitBox = new Rectangle(newX, newY, width, height);
            } else {
                hitBox.set(newX, newY, width, height);
            }
        }

        public void takeDamage(int damage) {
            hp = Math.max(0, hp - damage);
            healthBar.setCurrentHealth(hp);
        }

        public int getHp() {
            return hp;
        }

        public int getSegmentIndex() {
            return segmentIndex;
        }

        public void setToNewSegment(Vector2 newPosition, Direction newDir, int newSegmentIndex) {
            if (this.position == null) {
                this.position = new Vector2();
            }
            this.position.set(newPosition.x, newPosition.y);
            this.dir = newDir;
            this.segmentIndex = newSegmentIndex;
        }

        public int getDamageNumber(){
            return damage;
        }

        public int getMoney(){
            return money;
        }

        public int getMaxHp() {
            return maxHp;
        }

        public HealthBar createHealthBar(int hp, Vector2 position, float width, float height){
            return new HealthBar(hp, position, width, height);
        }

        public HealthBar getHealthBar() {
            return healthBar;
        }

        public boolean isDead() {
            return hp <= 0;
        }
    }
