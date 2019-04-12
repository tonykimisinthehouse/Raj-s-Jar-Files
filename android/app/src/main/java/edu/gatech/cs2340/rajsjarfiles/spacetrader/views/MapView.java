package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Planet;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.model.Model;

class MapView extends View {
    private static final float ANGLE_CHANGE = 0.001f;
    private static final int FULL_CIRCLE = 360;
    private static final int PLANET_TEXT_SIZE = 27;

    private static final int SUN_RADIUS = 30;
    private static final int ORBIT_RATIO = 75;
    private static final int RATIO = 7;

    private static float dAngle;

    private static final Random rand = new Random();

    android.os.Handler rotationHandler = new android.os.Handler(Looper.myLooper());

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        updateTimerThread.run();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        fillInBackground(canvas);
        fillInOrbit(canvas);
        fillInPlanet(canvas);
    }

    private final Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            incrementdAngle();
            invalidate();
            rotationHandler.postDelayed(this, 10);
        }
    };

    private void incrementdAngle() {
        dAngle += ANGLE_CHANGE;
        if (dAngle >= FULL_CIRCLE) {
            dAngle = 0;
        }
    }

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

    private void fillInOrbit(Canvas c) {
        Model m = Model.getModel();
        Planet[] planets = m.getPlanets();
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

    private void fillInPlanet(Canvas c){
        Model m = Model.getModel();
        Planet[] planets = m.getPlanets();        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        for (Planet planet: planets) {
            float[] coord = getXAndY(planet, c);
            paint.setColor(Color.parseColor(planet.getColorHex()));
            c.drawCircle(coord[0],coord[1],planet.getRadius() * RATIO, paint);
        }

        for (Planet planet: planets) {

            String distanceLabel = "";
            Planet currentPlanet = m.getPlanet();
            if (planet.equals(currentPlanet)) {
                paint.setColor(Color.RED);
            } else {
                paint.setColor(Color.WHITE);
                distanceLabel = currentPlanet.getDist(planet) + "LY";
            }

            float[] coord = getXAndY(planet, c);
            paint.setTextSize(PLANET_TEXT_SIZE);

            c.drawText(planet.getName() + " "
                    + distanceLabel, coord[0] + (planet.getRadius() * RATIO),
                    coord[1] - (planet.getRadius() * RATIO), paint);
        }
    }

    private float[] getXAndY(Planet planet, Canvas c) {

        float x = c.getWidth()/2;
        float y = c.getHeight()/2;
        float a = planet.getOrbitAngle() + dAngle;
        float h = (SUN_RADIUS * 2) + (planet.getOrbitRadius() * ORBIT_RATIO);

        float dx = 0;
        float dy = 0;

        if ((a >= 0) && (a < 90)) {
            dx = (float) (Math.sin(a) * h);
            dy = (float) (Math.cos(a) * h * -1);
        } else if ((a >= 90) && (a < 180)) {
            a -= 90;
            dx = (float) (Math.cos(a) * h);
            dy = (float) (Math.sin(a) * h);
        } else if ((a >= 180) && (a < 270)) {
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
}