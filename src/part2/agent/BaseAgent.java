package part2.agent;

import part2.Color;

public abstract class BaseAgent implements Agent {

    protected Color playerColor;

    BaseAgent(Color playerColor) {
        this.playerColor = playerColor;
    }

}
