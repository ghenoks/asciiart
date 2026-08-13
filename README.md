# ASCII Art

The idea of this project is to load images and translate them into ASCII ART images. Optionally you can apply filters, and save them. 

![asciiart example](src/main/scala/resources/ascii_art_example.png)

The app is a simple console-executable. 
1. Loads an image
2. Translates image into ASCII art
3. Applies filters if required - no filters by default
4. Outputs the image into an output

---
## How to run
1. Open sbt shell
2. run command consists of:
   1. image we want to load
   2. filters we want to apply
   3. how we want to save resulting image

Example:

```
run --image "src/main/scala/resources/pikachu.jpg" --rotate +90 --scale 0.25 --invert --output-console
```
---
## Loading Image

You Load an image using the --image* argument. Only one can be specified. 

There's 2 options for loading image: 

1. --image-random - generates a random image
2. --image "path" - path can be absolute or relative.
Supported formats are .jpg or .png.

---
# Filters

## Brightness

Changes greyscale value of pixels without losing any precision. 

--brightness value - +1, -1 etc.

If its over 255 or under 0 it stays 255 or 0.

## Flip

Flips the ASCII image on "x" or "y" axes.

--flip x or --flip y

## Invert

Inverts the greyscale value of pixels. 

Inversion is done as: Inverted grayscale = white - grayscale

--invert

## Rotate

Rotates the ASCII image by rotations dividable by 90 degrees.

--rotate degrees - degrees can be for example: 90, -90, 180, -180 etc.

## Scale

Scales the ASCII image. Supported scaling is 0.25, 1, 4.

--scale value 

---
## Output
You can print into console, save to a file, or both. 
1. --output-console - prints result into console
2. --output-file "path" - saves result into file. Path can be relative or absolute.
