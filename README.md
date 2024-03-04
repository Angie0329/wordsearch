# Word Search Application

### Dependencias Usadas

- Spring boot (3.2.4)
- Java 17

### Como ejecutar el proyecto

Para ejecutar el proyecto, sencillamente hay que entrar al root directory del mismo
y ejecutar el siguiente comando:

#### Linux / MacOS
```sh
$ ./gradlew bootRun
```

#### Windows
```cmd
$ .\gradlew.cmd bootRun
```

Una vez la aplicacion este totalmente arriba, se puede usar sin ningun problema

## Instrucciones de Uso

### Busqueda de palabras en horizontal y vertical

Para realizar una busqueda de una palabra de manera horizontal y/o vertical,
se debe hacer uso del endpoint `/search-horandvert` de la siguiente manera:

```txt
POST http://localhost:8080/api/v1/searchword/search-horandvert

{
    "searchword": "abcdefghijklmnopqrstuvwxyzabcdef",
    "rows": 4,
    "word": "tuv"
}

# y se debera recibir un resultado como este
# (en el caso de encontrar una palabra de manera vertical y/o horizontal)

{
    "searchword": "abcdefghijklmnopqrstuvwxyzabcdef",
    "rows": 4,
    "word": "tuv"
    "contains": true,
    "start_row": 2,
    "start_col": 3
}
```

La matriz usada para este ejemplo luce de la siguiente manera
```

  a    b    c    d    e    f    g    h
  i    j    k    l    m    n    o    p
  q    r    s    [t]  [u]  [v]  w    x
  y    z    a    b    c    d    e    f

```

### Busqueda de palabras en diagonal

Para realizar una busqueda de una palabra en en diagonal se debe hacer uso del
endpoint `/search` de la siguiente manera:

```txt
POST http://localhost:8080/api/v1/searchword/search

{
    "searchword": "aaufghthoxbaghthoecxbhthoeufgcth",
    "rows": 4,
    "word": "abc"
}

# y se debera recibir un resultado como este
# (en el caso de encontrar una palabra en diagonal)

{
    "rows": 4,
    "word": "abc",
    "contains": true,
    "wordsearch": "aaufghthoxbaghthoecxbhthoeufgcth",
    "start_row": 1,
    "start_col": 3
}
```

La matriz usada para este ejemplo luce de la siguiente manera
```

  a    a    u    f    g    h    t    h
  o    x    b    [a]  g    h    t    h
  o    e    c    x    [b]  h    t    h
  o    e    u    f    g    [c]  t    h

```

En ambos casos, si no se encontro la palabra especificada en la matriz, se
retornara un resultado como este:

```txt
{
    "rows": 4,
    "word": "tword",
    "contains": false,
    "wordsearch": "aaufghthoxbaghthoecxbhthoeufgcth"
}
```
