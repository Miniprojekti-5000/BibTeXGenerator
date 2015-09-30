import ohtu.miniprojekti5000.*
import ohtu.miniprojekti5000.logic.*
import ohtu.miniprojekti5000.ui.*

description "User can generate BibTeX from all added references" 

scenario "showing BibTeX succesfull", {
    given 'command make bibtex is given'
    when 'Hmm'
    then 'user sees added BibTeX in right form'
}