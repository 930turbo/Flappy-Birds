import pygame
import random

# Initialize game
pygame.init()

# Set window size
size = (700, 500)
screen = pygame.display.set_mode(size)

# Set title
pygame.display.set_caption("Flappy Bird")

# Load flappy bird image
flappy_img = pygame.image.load("flappy.png").convert_alpha()

# Initialize flappy bird
flappy_x = 350
flappy_y = 250
flappy_y_change = 0

# Initialize pipes
pipe_width = 75
pipe_height = 400
pipe_x = 700
pipe_y = random.randint(50, 350)
pipe_x_change = -3

# Initialize game variables
game_over = False
score = 0

# Game loop
while not game_over:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            game_over = True
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_SPACE:
                flappy_y_change = -5

    # Clear screen
    screen.fill((255, 255, 255))

    # Move flappy bird
    flappy_y += flappy_y_change
    flappy_y_change += 0.5

    # Draw flappy bird
    screen.blit(flappy_img, (flappy_x, flappy_y))

    # Move pipes
    pipe_x += pipe_x_change

    # Draw pipes
    pygame.draw.rect(screen, (0, 255, 0), (pipe_x, 0, pipe_width, pipe_y))
    pygame.draw.rect(screen, (0, 255, 0), (pipe_x, pipe_y + 150, pipe_width, pipe_height))

    # Check for collision with pipes
    if (flappy_x + flappy_img.get_width() > pipe_x and flappy_x < pipe_x + pipe_width):
        if (flappy_y < pipe_y or flappy_y + flappy_img.get_height() > pipe_y + 150):
            game_over = True

    # Check for collision with bottom and top of screen
    if (flappy_y > size[1] or flappy_y < 0):
        game_over = True

    # Update display
    pygame.display.update()
    pygame.time.Clock().tick(60)

    # Increase score
    if (pipe_x < flappy_x and pipe_x + pipe_width > flappy_x):
        score += 1

# End game
pygame.quit()
