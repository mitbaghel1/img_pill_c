import cv2
import numpy as np
import os

# Function to generate random color shift
def random_color_shift(image):
    hsv = cv2.cvtColor(image, cv2.COLOR_BGR2HSV)
    h, s, v = cv2.split(hsv)
    # Random shift values
    h_shift = np.random.randint(0, 180)
    s_shift = np.random.uniform(0.5, 1.5)
    v_shift = np.random.uniform(0.5, 1.5)
    
    # Applying shifts
    h = (h + h_shift) % 180
    s = np.clip(s * s_shift, 0, 255).astype(np.uint8)
    v = np.clip(v * v_shift, 0, 255).astype(np.uint8)
    
    # Merge the channels
    hsv = cv2.merge([h, s, v])
    
    return cv2.cvtColor(hsv, cv2.COLOR_HSV2BGR)


# Function to generate and save images with random color and rotation
def generate_images(image_path, output_dir, num_images):
    original_image = cv2.imread(image_path)
    if original_image is None:
        print("Error: Image not found.")
        return

    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    for i in range(num_images):
        # Random Rotation
        angle = np.random.randint(-45, 45)
        rows, cols, _ = original_image.shape
        rotation_matrix = cv2.getRotationMatrix2D((cols / 2, rows / 2), angle, 1)
        rotated_image = cv2.warpAffine(original_image, rotation_matrix, (cols, rows))

        # Random Color Shift
        colored_image = random_color_shift(rotated_image)
        #colored_image= cv2.bitwise_not(rotated_image)
        
        # Save the image
        cv2.imwrite(f"{output_dir}/image_{i+1}.jpg", colored_image)

    print(f"{num_images} images generated and saved in {output_dir}.")

# Path to the original image
image_path = 'F:\\Rollwala Computer Science Study\\testing_t.jpg'

# Output directory to save generated images
output_directory = 'F:\\Generated_Images'

# Number of images to generate
num_images = np.random.randint(15, 21)

# Generate and save images
generate_images(image_path, output_directory, num_images)

#git diff --stat origin/master..HEAD
#git diff --name-only origin/master..HEAD
#git stash list
#git stash show -p stash@{<number>} > <name>.patch
