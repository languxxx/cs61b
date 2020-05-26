public class NBody {
    public static double readRadius(String file) {
        In in = new In(file);
        int numPlanets = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String file) {
        In in = new In(file);
        int numPlanets = in.readInt();
        Body[] Bodies = new Body[numPlanets];
        double radius = in.readDouble();

        double xP, yP, xV, yV, m;
        String img;
        for(int i = 0;i < numPlanets; i++) {
            xP = in.readDouble();
            yP = in.readDouble();
            xV = in.readDouble();
            yV = in.readDouble();
            m = in.readDouble();
            img = in.readString();
            Bodies[i] = new Body(xP,yP,xV,yV,m,img);
        }
        return Bodies;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Body[] bodies = readBodies(filename);
        double r = readRadius(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-r, r);
        StdDraw.clear();

        double t = 0;
        while(t<=T) {

            double xForces[] = new double[bodies.length], yForces[] = new double[bodies.length];
            int i = 0;
            for (Body b : bodies) {
                xForces[i] = b.calcNetForceExertedByX(bodies);
                yForces[i] = b.calcNetForceExertedByY(bodies);
                i++;
            }
            i = 0;
            for (Body b : bodies) {
                b.update(dt,xForces[i],yForces[i]);
                i++;
            }
            StdDraw.picture(0, 0, "images/starfield.jpg",2*r,2*r);
            for (Body b : bodies) {
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t = t + dt;
        }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
        //StdDraw.pause(2000);
    }
}
