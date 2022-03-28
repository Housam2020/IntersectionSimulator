public class SignalLight {

    int[] lights = new int[4];       //Directions represented as (N,S,E,W), 0 = red, 1 = green
    public SignalLight(){    //Initialize lights
        lights[0] = 1;
        lights[1] = 1;
        lights[2] = 0;
        lights[3] = 0;
    }

    public void LightCycle() {      //Call this function to change the light cycle
        if (lights[0] == 1 && lights[1] == 1 && lights[2] == 0 && lights[3] == 0) {
            lights[0] = 0;
            lights[1] = 0;
            lights[2] = 1;
            lights[3] = 1;
        }

        else if (lights[0] == 0 && lights[1] == 0 && lights[2] == 1 && lights[3] == 1) {
            lights[0] = 1;
            lights[1] = 1;
            lights[2] = 0;
            lights[3] = 0;
        }
    }



}
