package src.main.chapter1;

import main.Vector;

public class Environment {
    public Vector gravity;
    public Vector wind;

    public Environment(Vector gravity, Vector wind) {
        this.gravity = gravity;
        this.wind = wind;
    }
}
