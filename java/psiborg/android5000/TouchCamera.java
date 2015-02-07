package psiborg.android5000;

import psiborg.android5000.base.Camera;
import psiborg.android5000.base.GameObject;
import psiborg.android5000.util.Matrix;
import psiborg.android5000.util.Quaternion;
import psiborg.android5000.util.Vector3;
import psiborg.freespace.Sensors;

public class TouchCamera extends GameObject {
    public static float yaw, pitch, radius = 2.5f;
    private Camera cam;
    @Override
    public void load() {
        cam = new Camera(
                new float[]{3.0f,  3.0f,  3.0f},
                new float[]{0.0f,  0.0f,  0.0f},
                new float[]{0.0f,  0.0f,  1.0f},
                70f,1f,10f);
        cam.setMain();
    }
    @Override
    public void step() {
        /*
        pitch = (float)(Math.max(Math.min(pitch, Math.PI/2-.001),-Math.PI/2+.001));
        cam.updateLook(new float[]{(float)(Math.cos(yaw)*Math.cos(pitch)*radius),
                                   (float)(Math.sin(pitch)*radius),
                                   (float)(Math.sin(yaw)*Math.cos(pitch)*radius)},
                       new float[]{0,0,0},
                       new float[]{0,1,0});
        */
        Quaternion q = new Quaternion(Sensors.gyro.x, Sensors.gyro.y, Sensors.gyro.z);
        cam.updateLook(q.forward().mult(-radius), Vector3.zero, q.up());
    }
    @Override
    public void unload() {
        if (Camera.active.equals(cam)) {
            Camera.active = null;
        }
    }
}
