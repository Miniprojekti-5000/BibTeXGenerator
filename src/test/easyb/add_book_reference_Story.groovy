//import ohtu.miniprojekti5000.*
//import ohtu.miniprojekti5000.logic.*
//import ohtu.miniprojekti5000.ui.*
//
//description "User can add book -reference"
//
//scenario "'adding book -reference' succesfull when not using scandic letters", {
//    given 'command add book is given', {
//        io = new TestIO(1, "Some headr", "Ville Vallaton", "title", "Otawa", "1888", " ", " ")
//        co = new Controller(io)
//    }
//    when 'valid informations are entered', {
//        co.start();
//    }
//    then 'user sees confirmationtext "Book was succesfully added."', {
//        io.getConfirmation().shouldEqual("Book was successfully added.")
//    }
//}
