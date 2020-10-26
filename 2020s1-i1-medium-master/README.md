# UNQ » UIs » Medium

[![JitPack](https://jitpack.io/v/unq-ui/2020s1-i1-medium.svg)](https://jitpack.io/#unq-ui/2020s1-i1-medium)

Medium es una plataforma donde los usuarios son autores que pueden escribir y comentar notas.

## Usar como dependencia

Agregar el repositorio:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Agregar la dependencia:

```xml
<dependency>
    <groupId>com.github.unq-ui</groupId>
    <artifactId>2020s1-i1-medium</artifactId>
    <version>1.0.1</version>
</dependency>
```

## Especificación de Dominio

Toda interacción con el dominio se hace a través de la clase `MediumSystem`.
La programación del dominio ya es proveído por la cátedra.

Para utilizar el _system_ con información existen dos maneras.

Puede generar de cero la instancia y agregar la información que necesite:

```kotlin
import org.ui.MediumSystem
import org.ui.DraftNote

val system = MediumSystem()

val a1 = system.registerAuthor("Name 1","name1@gmail.com", "pass1","https://pix.example/1.png")
val a2 = system.registerAuthor("Name 2","name2@gmail.com", "pass2","https://pix.example/2.png")
val a3 = system.registerAuthor("Name 3","name3@gmail.com", "pass3","https://pix.example/3.png")

system.addNote(a1.id, DraftNote("Mi nota 1", "My body 1", "tag1,tag2"))
system.addNote(a2.id, DraftNote("Mi nota 2", "My body 2", "tag2,tag3"))
system.addNote(a3.id, DraftNote("Mi nota 3", "My body 3", "tag1,tag3"))
```

O puede utilizar el _system_ pre-cargado por la cátedra:

```kotlin
import org.ui.bootstrap.getMediumSystem

val system = getMediumSystem()
```

### `MediumSystem`

Es el _entry point_ del sistema, consta de la siguiente interfaz:

```kotlin
// @Throw NotFound si `noteId` no existe
fun getNote(noteId: String): Note

// @Throw NotFound si `authorId` no existe
fun getAuthor(authorId: String): Author

// @Throw UsedEmail si `email` ya está en uso
fun registerAuthor(name: String, email: String, password: String, photo: String): Unit

// @Throw NotFound si `email` o `password` son incorrectos
fun login(email: String, password: String): Author

// @Throw NotFound si `authorId` no existe
// @Throw RepeatedTitle si `author` ya tiene una nota con el mismo título
fun addNote(authorId: String, draftNote: DraftNote): Unit

// @Throw NotFound si `noteId` no existe
fun editNote(noteId: String, draftNote: DraftNote): Unit

fun removeNote(noteId: String): void

// @Throw NotFound si `noteId` o `authorId` no existen
fun addComment(noteId: String, authorId: String, comment: DraftComment): void

fun searchNotesByAuthorId(authorId: String): List<Note>

fun searchNotesByCategory(category: String): List<Note>

fun searchNotesByTitle(title: String): List<Note>

fun latestAddedNotes(): List<Note>
```

### `Author`

Es el usuario del sistema.

```kotlin
data class Author(
  val id: String,
  val name: String,
  val email: String,
  var password: String,
  var photo: String
)
```

### `Note`

La nota ya creada.

```kotlin
data class Note(
  val id: String,
  var title: String,
  var body: String,
  var categories: List<String>,
  val author: Author,
  val comments: MutableList<Comment>
)
```

### `DraftNote`

La nota antes de ser guardada por el sistema.

```kotlin
data class DraftNote(
  var title: String,
  var body: String,
  var categories: String
)
```

### `Comment`

El comentario que se guarda dentro de una nota.

```kotlin
data class Comment(
  val id: String,
  val author: Author,
  val body: String
)
```

### `DraftComment`

El comentario antes de ser guardado por el sistema.

```kotlin
data class DraftComment(val body: String)
```
