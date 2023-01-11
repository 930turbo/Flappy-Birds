#include <SFML/Graphics.hpp>

const int WINDOW_WIDTH = 800;
const int WINDOW_HEIGHT = 600;
const int FLAPPY_SIZE = 50;
const float GRAVITY = 0.1f;
const float JUMP_SPEED = -5.0f;

class Flappy {
    private:
        sf::RectangleShape flappy;
        float speed;
    public:
        Flappy() {
            flappy.setSize(sf::Vector2f(FLAPPY_SIZE, FLAPPY_SIZE));
            flappy.setFillColor(sf::Color::Yellow);
            flappy.setPosition(WINDOW_WIDTH/2, WINDOW_HEIGHT/2);
            speed = 0.0f;
        }

        void update() {
            speed += GRAVITY;
            flappy.move(0.0f, speed);
        }

        void jump() {
            speed = JUMP_SPEED;
        }

        sf::RectangleShape getShape() {
            return flappy;
        }
}

int main() {
    sf::RenderWindow window(sf::VideoMode(WINDOW_WIDTH, WINDOW_HEIGHT), "Flappy Bird");

    Flappy flappy;

    while (window.isOpen()) {
        sf::Event event;
        while (window.pollEvent(event)) {
            if (event.type == sf::Event::Closed) {
                window.close();
            }
            if (event.type == sf::Event::KeyPressed) {
                if (event.key.code == sf::Keyboard::Space) {
                    flappy.jump();
                }
            }
        }

        flappy.update();

        window.clear();
        window.draw(flappy.getShape());
        window.display();
    }

    return 0;
}
