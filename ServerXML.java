"<mensa>"


	"<studenti>
		<studente idstudente="++">
			<nome></nome>
			<cognome></cognome>
			<nascita></nascita>
			<telefono></telefono>
			<classe></classe>
			<indirizzo></indirizzo>
			<citta></citta>
		</studente>
	</studenti>"
	
	
</mensa>

try{
            // ESEGUO UNA QUERY DI SELEZIONE
			
			XmlString=  "<mensa>"+
						"<studenti>";
            PreparedStatement sel = connection.prepareStatement("SELECT * FROM studenti");
            resultSet=sel.executeQuery();

            int cont = 1;
            while(resultSet.next()){
			XMLString= XMLString+ "<studente idstudente="+resultSet.getString("idstudente")+">"+
									"<nome>"+resultSet.getString("nome")+"</nome>"+
									"<cognome>"+resutlSet.getString("cognome")+"</cognome>"+
									"<datanascita>"+resultSet.getString("datanascita")+"</datanascita>"+ //datanascita controllare nel database come è scritto
									"<telefono>"+resultSet.getString("telefono")+"</telefono>"+ //telefono controllare come eè scritto
									//macnca classe indirizzo città e menu e piatti
               resultSet.getString("email")
               cont++;
            }
        }catch(SQLException sql_ex){
            sql_ex.printStackTrace();
        }