# Steps

Go to the `source` directory first. Then type in the following:

```
javac -d ../bin Controller.java
```

This will create a `bin` folder that contains only the `.class` files.

Create a `manifest.txt` file with the following contents:

```text
Main-Class: Controller
```

but make sure that there's an empty line after above line.

Make a folder called OutFolder. Go to the bin folder and type the following:

```text
jar -cvmf manifest.txt ../OutFolder/kingskey.jar *
```

And you're done.

# To Run

## Via bin Folder

Go to the `bin` folder and type:

```
java Controller
```

and the program should run.

## Via jar File

Go into OutFolder and run the following command:

```text
java -jar kingskey.jar
```