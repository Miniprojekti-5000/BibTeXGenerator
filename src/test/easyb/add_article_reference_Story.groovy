//import ohtu.miniprojekti5000.*
//import ohtu.miniprojekti5000.logic.*
//import ohtu.miniprojekti5000.ui.*
//
//description "User can add article -reference"
//
//scenario "'adding article -reference' succesfull when not using scandic letters", {
//    given 'command add book is given', {
//        io = new TestIO(2, "Some headr", "Ville Vallaton", "title", " ", "1888", "journal", "volume")
//        co = new Controller(io)
//    }
//    when 'valid informations are entered', {
//        co.start();
//    }
//    then 'user sees confirmationtext "Article was succesfully added."', {
//        io.getConfirmation().shouldEqual("Article was successfully added.")
//    }
//}
