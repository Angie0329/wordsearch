# Word Search Application

### Used Units

- Spring boot (3.2.4)
- Java 17

### How to execute the project

To run the project, simply enter the root directory of the project and run the following command
and execute the following command:

#### Linux / MacOS
```sh
$ ./gradlew bootRun
```

#### Windows
```cmd
$ .\gradlew.bat bootRun
```

Once the application is fully up, you can use it without any problem.

## Instructions for Use

### Horizontal and vertical word search

To search for a word horizontally and/or vertically,
the `/search-horandvert` endpoint must be used as follows:

```txt
POST http://localhost:8080/api/v1/wordsearch/search-horandvert

{
    "searchword": "abcdefghijklmnopqrstuvwxyzabcdef",
    "rows": 4,
    "word": "tuv"
}

# and you should receive a result like this
# (in the case of finding a word vertically and/or horizontally)

{
    "searchword": "abcdefghijklmnopqrstuvwxyzabcdef",
    "rows": 4,
    "word": "tuv"
    "contains": true,
    "start_row": 2,
    "start_col": 3
}
```

The matrix used for this example looks like this
```

  a    b    c    d    e    f    g    h
  i    j    k    l    m    n    o    p
  q    r    s    [t]  [u]  [v]  w    x
  y    z    a    b    c    d    e    f

```

### Diagonal word search

To perform a diagonal word search you must use the
endpoint `/search` as follows:

```txt
POST http://localhost:8080/api/v1/wordsearch/search

{
    "searchword": "aaufghthoxbaghthoecxbhthoeufgcth",
    "rows": 4,
    "word": "abc"
}

# and you should receive a result like this
# (in the case of finding a word in diagonal)

{
    "rows": 4,
    "word": "abc",
    "contains": true,
    "wordsearch": "aaufghthoxbaghthoecxbhthoeufgcth",
    "start_row": 1,
    "start_col": 3
}
```

The matrix used for this example looks like this
```

  a    a    u    f    g    h    t    h
  o    x    b    [a]  g    h    t    h
  o    e    c    x    [b]  h    t    h
  o    e    u    f    g    [c]  t    h

```

In both cases, if the specified word was not found in the array, a result like this will be returned.
will return a result like this:

```txt
{
    "rows": 4,
    "word": "tword",
    "contains": false,
    "wordsearch": "aaufghthoxbaghthoecxbhthoeufgcth"
}
```
