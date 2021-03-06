# CodeGenerator
A command line program for quickly generating starting code(generates java for now but it can be extended). Useful if you are not using any IDE or you just want to generate starting code using the cli.

You can change the comments(header, constructors, setters, getters, functions) by creating your own .txt file
beside your jar file (names should be header.txt, constructors.txt, setters.txt, getters.txt, functions.txt).

There are two ways to generate a java file:

1.) Using mapped args separated by '@' symbol which are then mapped to the format
    you specified in config.txt file. The default config for list of commands is:

    type@clsname@extends@implements@vars@fns@constructors@pname@imports

    You can change it by adding config.txt file beside your jar file.

    You can also set the default pname and language(only java for now) in the config file like so:
         pname = Your name here
         language = java

    Example usage:
        java -jar codegenerator.java "c@TestClassName@HelloParentClass@HelloInterface"
        ^
        |
        + - - This will be a class with a name of TestClassName extending HelloParent and implementing HelloInterface.
              Note that the commands vars - imports are neglected since the args only spans upto the implements command.

    You can skip the command by just putting a space or nothing in for that specific command.

    Example:
        java -jar codegenerator.java "i@TestClassName@HelloParent"
        ^
        |
        + - - This will be an interface named TestClassName extending HelloParent with no functions.

    You can separate args by using ';'.

    Example:
        java -jar codegenerator.java "a@TestClassName@HelloParentClass@HelloInterface1;HelloInterface2@String|str;int|n"
        ^
        |
        + - - This will be an abstract class that extends HelloParentClass and implements HelloInterface1 and
              HelloInterface2. It has variables str(String) and n(int).

    Note: - For constructors, if you want to enter the default constructor, just use a space as an argument.
          - Default classname is ClassName

    Example:
        java -jar codegenerator.java "@@@@@@ @"

2.) Using a step by step setting of commands. Using your terminal just execute your jar file
    with/without the language like so:
        java -jar codegenerator.java [language]
        ^
        |
        + - - language is optional and java is the default. It's the only language supported for now.

    You can then set the variables, functions, etc. in your class by using the form:
    command: commandArgsSeparatedBySemicolonIfMany

    Note: To generate, just type create. To quit, just type quit.

    where command can be any of the following:

    -clsname        ------> Class name
    -vars           ------> Variables. See below
    -rmvars         ------> Remove variables given a list of names separated by ;
    -fns            ------> Functions. See below
    -rmfns          ------> Remove functions separated by ; given the name together with params. Format is
                            funcName(ParamDataType1|ParamDataType2, ...)
    -type           ------> Type. Of the form: [accessibility][static][final]type
                            Where: accessibility can be + for pubnlic, - for private and # for protected.
                                  static must be ^
                                  final must be !
                                  type can be i for interface, c for class and a for abstract class
    -pname          ------> Programmer name
    -constructors   ------> Constructors. See below
    -rmconstructors ------> Remove constructors separated by ; of the form:
                            ParamDataType1|ParamName1[;...]
    -imports        ------> Imports
    -rmimports      ------> Remove imports separated by ;
    -implements     ------> Implements
    -rmimplements   ------> Remove implements separated by ;
    -extends        ------> Extends
    -rmextends      ------> Remove extends separated by ;
    -language       ------> Language

The full format of a variable arg:
    [accessibility][static][final]DataType|variableName[([s[:newSetterName][,g:newGetterName]])]

    Where:
        accessibility can be any of the following: + for public, - for private and # for protected
        static must be ^
        final must be !
        DataType can any valid Java DataType name
        variableName can also be any valid Java variable name

    Defaults:
        private not static not final type of variable.

    Note: By default, setters and getters will be generated and are based on the name of the variable. If you want,
          you can change and remove setters and getters.

          Example for changing the setter name:
                String|str(s:newName, g:str)

          Example for changing the setter name but removing the getter:
                String|str(s:newName)

          Example for using only the setter and removing the getter:
                String|int(s)

          Example for removing the setter and getter:
                String|int()

          The same can be done with the getter.

The full format for a function arg:
    [accessibility][static][final]DataType|functionName[(paramDataType1|paramName1[,paramDataType2|paramName2, ...])]

    Where:
        accessibility can be any of the following: + for public, - for private and # for protected
        static must be ^
        final must be !
        DataType can any valid Java DataType name
        variableName can also be any valid Java variable name

     Defaults:
        A public non static non final function

        Example minimum form for adding a function:
            String|func
            ^
            |
            + - - It can also be String|func()

        Example static final function with arguments:
            +^!String|func(int|n, boolean|b)

The full format for constructor arg:
    [paramDataType1|paramName1[,paramDataType2|paramName2,...]]

That is optional because you can enter nothing(space if you use the first way of entering your commands) for the default constructor