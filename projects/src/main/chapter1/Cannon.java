package src.main.chapter1;
import main.Vector;
import main.Point;
import main.Tuple;

public class Cannon {

    public static void main(String[] args) {
        Vector v1 = new Vector(1, 1, 0);
        Tuple vec = v1.normalize();
     
        Projectile proj = new Projectile(new Point(0, 1, 0), vec);
        Environment env = new Environment(new Vector(0, -0.1, 0), new Vector(-0.01, 0, 0));
        int count = 0;

        while(proj.position.getY() >=0) {
            count++;
            proj = tick(env, proj);
            System.out.println(proj.position.getY());
            System.out.println("Count :"+count);
        }
     }

    private static Projectile tick(Environment env, Projectile proj) {
        Tuple position =  proj.position.add(proj.velocity);
        Tuple velocity =  proj.velocity.add(env.gravity).add(env.wind);
        
        return new Projectile(position, velocity); 
    }
}