package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Planet;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.model.Model;

/**
 * Customized view for solar System Map
 */
class MapView extends View {

    private static final int SUN_RADIUS = 30;
    private static final int ORBIT_RATIO = 75;
    private static final int RATIO = 7;

    private static float dAngle = 0;

    android.os.Handler rotationHandler = new android.os.Handler(Looper.myLooper());

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        updateTimerThread.run();
    }

    /**
     * onDraw methods (starts when view is created and shown)
     * @param canvas canvas to draw on
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        fillInBackground(canvas);
        fillInOrbit(canvas);
        fillInPlanet(canvas);
    }

    private Runnable updateTimerThread = new Runnable()
    {
        public void run()
        {
            incrementdAngle();
            invalidate();
            rotationHandler.postDelayed(this, 10);
        }
    };

    /**
     * Increment angle of every planet periodically for animation
     */
    private void incrementdAngle() {
        dAngle += 0.1;
        if (dAngle >= 360) {
            dAngle = 0;
        }
    }

    /**
     * Fill in black background to the canvas
     * @param c canvas to draw on
     */
    private void fillInBackground(Canvas c) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        c.drawPaint(paint);

        int x = c.getWidth()/2;
        int y = c.getHeight()/2;

        paint.setColor(Color.YELLOW);
        c.drawCircle(x, y, SUN_RADIUS * 2, paint);
    }

    /**
     * Fill in gray orbits to the canvas
     * @param c canvas to draw on
     */
    private void fillInOrbit(Canvas c) {
        Planet[] planets = Model.getCurrent().getPlayer().getLocation().getSolarSystem().getPlanets();
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        int x = c.getWidth()/2;
        int y = c.getHeight()/2;

        for (Planet planet: planets) {
            int or = planet.getOrbitRadius();
            c.drawCircle(x,y, (SUN_RADIUS * 2) + (or * ORBIT_RATIO), paint);
        }
    }

    /**
     * Fill in planets on to the canvas
     * @param c canvas to draw on
     */
    private void fillInPlanet(Canvas c){

        Planet[] planets = Model.getCurrent().getPlayer().getLocation().getSolarSystem().getPlanets();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        for (Planet planet: planets) {
            float[] coord = getXAndY(planet, c);
            paint.setColor(Color.parseColor(planet.getHabitats().getColorHex()));
            c.drawCircle(coord[0],coord[1],planet.getRadius() * RATIO, paint);
        }

        for (Planet planet: planets) {

            String distanceLabel = "";
            Planet currentPlanet = Model.getCurrent().getPlayer().getLocation().getPlanet();
            if (planet.equals(currentPlanet)) {
                paint.setColor(Color.RED);
            } else {
                paint.setColor(Color.WHITE);
                distanceLabel = currentPlanet.getDist(planet) + "LY";
            }


            float[] coord = getXAndY(planet, c);
            paint.setTextSize(27);

            c.drawText(planet.getName() + " " + distanceLabel,
                    coord[0] + planet.getRadius() * RATIO , coord[1] - planet.getRadius() * RATIO, paint);

            // Draw Warp Zone Indicator
            if (planet.getIsWarpZone()) {
                paint.setColor(Color.parseColor("#01b9ff"));
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(5);
                c.drawCircle(coord[0], coord[1] - planet.getRadius() * RATIO - 20, RATIO,paint);
                c.drawCircle(coord[0], coord[1] - planet.getRadius() * RATIO - 20, RATIO + 10,paint);
            }
            paint.reset();
        }
    }

    /**
     * Get X and Y coordinates on canvas of the planet
     * @param planet planet to be drawn
     * @param c canvas to be drawn on
     * @return array [0] = x, [1] = y
     */
    private float[] getXAndY(Planet planet, Canvas c) {

        float x = c.getWidth()/2, y = c.getHeight()/2;
        double a = planet.getOrbitAngle() + dAngle;

        if (a >= 360) a -= 360;
        a = Math.toRadians(a);

        float h = SUN_RADIUS * 2 + planet.getOrbitRadius() * ORBIT_RATIO;

        float dx = 0, dy = 0;

        if (a >= 0 && a < 90) {
            dx = (float) (Math.sin(a) * h);
            dy = (float) (Math.cos(a) * h * -1);
        } else if (a >= 90 && a < 180) {
            a -= 90;
            dx = (float) (Math.cos(a) * h);
            dy = (float) (Math.sin(a) * h);
        } else if (a >= 180 && a < 270) {
            a -= 180;
            dx = (float) (Math.sin(a) * h * -1);
            dy = (float) (Math.cos(a) * h);
        } else {
            a -= 270;
            dx = (float) (Math.cos(a) * h * -1);
            dy = (float) (Math.sin(a) * h * -1);
        }

        x += dx;
        y += dy;

        float[] array = new float[2];
        array[0] = x;
        array[1] = y;

        return array;
    }

    /**
     * Turn off the animation when activity is in paused
     */
    protected void turnOffView() {
        rotationHandler.removeCallbacks(updateTimerThread);
    }
}