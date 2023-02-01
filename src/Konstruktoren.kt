/*
TODO: Schreibe eine Klasse Hund.
Ein Hund hat ein Alter, ein Geschlecht, einen Namen und kann bellen und sein Alter in Hunde Jahren zurückgeben.

Die Klasse hat zwei Konstruktoren:
*   Einen primären Konstruktor: Hier werden die Parameter Alter, Geschlecht und der Name einfach dem Hund zugewiesen.
*   Einen sekundären Konstruktor: Hier wird ein neuer Hund geboren, das Alter des Hundes ist deshalb 0.
    Das Geschlecht des Hundes ist zufällig und der Name des Hundes soll über die Konsole eingegeben werden (readln()).

Erstelle dann zwei Hunde:
*   Einen mit beliebigen Alter, Geschlecht und Name (primärer Konstruktor).
*   Einen Neugeborenen (sekundärer Konstruktor).
*/




class Dog(var age: Int, var gender: String, var name: String) {
    init {
        age = age * 7
    }

    fun bark() = println("Woof!")
    fun getAgeInDogYears() = age

    constructor(name: String): this(0, if (Math.random() < 0.5) "male" else "female", name)
}

fun main() {
    val dog1 = Dog(5, "male", "Max")
    println("Dog 1: Age - ${dog1.age}, Gender - ${dog1.gender}, Name - ${dog1.name}")

    print("Enter the name of the newborn dog: ")
    val newbornName = readLine()!!
    val dog2 = Dog(newbornName)
    println("Dog 2: Age - ${dog2.age}, Gender - ${dog2.gender}, Name - $newbornName")
}
/*

TODO: Schreibe eine Klasse Auto und eine Unterklasse Cabriolet.
Ein Auto hat eine PS-Anzahl, eine Anzahl an Räder, eine Anzahl an Sitzen, ein Baujahr und es hat Automatik oder nicht.

Die Klasse hat zwei Konstruktoren:
*   Einen primären Konstruktor: Hier werden die übergebenen Parameter der Klasse zugewiesen.
*   Einen sekundären Konstruktor: Hier werden als Parameter einen Autotyp und ein Baujahr übergeben.
    Im Konstruktor wird dann anhand des Autotyps ein Auto erstellt.
    Autotyp:    - Bus -> 300 PS, 6 Räder, 42 Sitze, hat Automatik.
                - Lkw -> 450 PS, 10 Räder, 2 Sitze, hat Automatik.
                - Pkw -> 100 PS, 4 Räder, 5 Sitze, hat Automatik.

Schreibe dann eine Unterklasse Cabriolet, dass von der Klasse Auto erbt.
Ein Cabriolet kann sein Dach ein- und ausfahren.
Dabei soll eine Warnung ausgegeben werden, wenn man versucht das Dach zu schließen, wenn es bereits zu ist,
bzw. zu öffnen, wenn es bereits offen ist.
Außerdem ist ein Cabriolet immer vom Typ Pkw.
*/

open class Car(var horsepower: Int, var wheels: Int, var seats: Int, var year: Int, var isAutomatic: Boolean) {
    constructor(type: String, year: Int) : this(0, 0, 0, year, true) {
        when (type) {
            "Bus" -> {
                horsepower = 300
                wheels = 6
                seats = 42
            }
            "Lkw" -> {
                horsepower = 450
                wheels = 10
                seats = 2
            }
            "Pkw" -> {
                horsepower = 100
                wheels = 4
                seats = 5
            }
        }
    }
}

class Cabriolet : Car(100, 4, 5, 0, true) {
    private var roofOpen = false

    fun toggleRoof() {
        if (roofOpen) {
            println("Closing roof")
            roofOpen = false
        } else {
            println("Opening roof")
            roofOpen = true
        }
    }
}

fun main() {
    val car = Car("Pkw", 2020)
    println("Car: HP - ${car.horsepower}, Wheels - ${car.wheels}, Seats - ${car.seats}, Year - ${car.year}, Automatic - ${car.isAutomatic}")

    val cabriolet = Cabriolet()
    println("Cabriolet: HP - ${cabriolet.horsepower}, Wheels - ${cabriolet.wheels}, Seats - ${cabriolet.seats}, Year - ${cabriolet.year}, Automatic - ${cabriolet.isAutomatic}")

    cabriolet.toggleRoof()
    cabriolet.toggleRoof()
}


/*
TODO: Schreibe eine Klasse Person sowie die Unterklassen Lehrer.
die Person hat einen Namen, ein Alter, eine Telefonnummer und eine Körpergröße.
Stelle im Konstruktor sicher, dass keine Person mit negativer Körpergröße
und keine Person mit negativem Alter erstellt werden kann.
Eine Person kann sich vorstellen und altern.

Ein Lehrer erbt von der Klasse Person und lehrt entweder in der Haupt-, Realschule oder im Gymnasium.
Zusätzlich lehrt der Lehrer in zwei Fächern aus den Fächern Mathe, Deutsch, Englisch, Französisch, Sport.
Stelle im Konstruktor sicher, dass er genau zwei Fächer lehrt, dass die Fächer stimmen und die Schule stimmt.
Man kann den Lehrer fragen welche Fächer, bzw. in welcher Schule er lehrt.
Wenn der Lehrer älter wird, als 65 Jahre, antwortet er auf die Fragen damit, dass er in Rente ist.
*/
class Person(var name: String, var age: Int, var phoneNumber: String, var height: Double) {
    init {
        if (age < 0) throw IllegalArgumentException("Age can't be negative")
        if (height < 0) throw IllegalArgumentException("Height can't be negative")
    }
    fun introduce() {
        println("Hi, my name is $name, I am $age years old, my number is $phoneNumber, and I am $height tall.")
    }
    fun age() {
        age++
    }
}

class Teacher(name: String, age: Int, phoneNumber: String, height: Double, var schoolType: String, var subject1: String, var subject2: String) : Person(name, age, phoneNumber, height) {
    init {
        if (subject1 !in arrayOf("Mathe", "Deutsch", "Englisch", "Französisch", "Sport")) throw IllegalArgumentException("Invalid subject")
        if (subject2 !in arrayOf("Mathe", "Deutsch", "Englisch", "Französisch", "Sport")) throw IllegalArgumentException("Invalid subject")
        if (schoolType !in arrayOf("Hauptschule", "Realschule", "Gymnasium")) throw IllegalArgumentException("Invalid school type")
        if (subject1 == subject2) throw IllegalArgumentException("Teacher can't teach the same subject twice")
    }
    fun whatDoYouTeach() {
        if (age > 65) {
            println("I am in retirement.")
        } else {
            println("I teach $subject1 and $subject2 in $schoolType.")
        }
    }
}


