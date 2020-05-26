public class Body {
    public double xxPos, yyPos, xxVel, yyVel, mass;
    String imgFileName;
    static final double G = 6.67e-11;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        return Math.sqrt(Math.pow(this.xxPos - b.xxPos, 2) + Math.pow(this.yyPos - b.yyPos, 2));
    }

    public double calcForceExertedBy(Body b) {
        double r = this.calcDistance(b);
        return G*this.mass*b.mass/Math.pow(r,2);
    }

    public double calcForceExertedByX(Body b) {
        double r = this.calcDistance(b),
                F = this.calcForceExertedBy(b),
                dx = b.xxPos - this.xxPos;
        return F*dx/r;
    }

    public double calcForceExertedByY(Body b) {
        double r = this.calcDistance(b),
                F = this.calcForceExertedBy(b),
                dy = b.yyPos - this.yyPos;
        return F*dy/r;
    }

    public double calcNetForceExertedByX(Body[] all) {
        double netFx = 0;
        for(Body b : all) {
            if (this.equals(b)) continue;
            netFx += calcForceExertedByX(b);
        }
        return netFx;
    }

    public double calcNetForceExertedByY(Body[] all) {
        double netFy = 0;
        for(Body b : all) {
            if (this.equals(b)) continue;
            netFy += calcForceExertedByY(b);
        }
        return netFy;
    }

    public void update(double dt, double Fx, double Fy) {
        double ax = Fx/mass, ay = Fy/mass;
        xxVel += dt*ax;
        yyVel += dt*ay;
        xxPos += dt*xxVel;
        yyPos += dt*yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}
