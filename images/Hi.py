from PIL import Image
import numpy as np
import os

def resizeNum(img, width, height):
    img = img.resize((width, height),Image.ANTIALIAS)
    return img

def splitAlphabet(starting_index, save_index):
    loadPath = r"D:\Machine learning\Alphabet Img\Origin\num_"
    savePath = r"D:\Machine learning\Alphabet Img\Splited\num_"
    index = starting_index
    while True:
        try:
            image = Image.open(loadPath + str(index) + ".PNG").convert("L")
            size = image.size
            height = size[1] / 4
            width = size[0] / 13
            for j in range(4):
                for i in range(13):
                    box = (i * width, j * height, (i+1) * width, (j + 1) * height)
                    cropped_image = image.crop(box)
                    cropped_image = resizeNum(cropped_image, 32, 32)
                    cropped_image.save(savePath + str(index + save_index) + "_" + str(13 * j + i) + ".png")
            print("index "+ str(index) + " complete")
        except:
            print("load until index = " + str(index))
            break
        index += 1

def turnImgToNumpy():
    image = Image.open(r"D:\Machine learning\Number Img\splited\num_0_0.png")
    image = np.array(image).reshape(1,1,28,28)
    loadPath = r"D:\Machine learning\Number Img\splited\num_"
    
    for i in range(10):
        for j in range (10):
            tmp = Image.open(loadPath + str(i) + "_" + str(j) + ".png")
            tmp = np.array(tmp).reshape(1,1,28, 28)
            image = np.append(image, tmp, axis = 0)
savePath = r"D:\Programming\GreenFoot\TankGame\images\ExplodeEffect\Explode_"
image = Image.open(r"D:\Programming\GreenFoot\TankGame\images\Explode.png")
size = image.size
height = size[1] / 8
width = size[0] / 9
for j in range(8):
    for i in range(9):
        box = (i * width, j * height, (i+1) * width, (j + 1) * height)
        cropped_image = image.crop(box)
        cropped_image = resizeNum(cropped_image, 50, 50)
        cropped_image.save(savePath + str(64 - (8 * j + i)) + ".png")
