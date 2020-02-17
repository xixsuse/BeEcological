INSERT INTO beecological.user VALUE 
	("sixpain","11111111","Danilo","DellOrco","danilodellorcolp@gmail.com",3311072846,0),
    ("stormjack","22222222","Jacopo","Fabi","jacopo.fabi1997@gmail.com",3932868752,0);
    
INSERT INTO beecological.center VALUE
	("The Trasher","0775000000","Alatri","Via della mola","03011","23"),
	("La vecchia mola","0775111111","Ravenna","Viale abolfo","15663","11"),
    ("Verona pulita","0245512368","Verona","Via nonna luisa","54642","2"),
	("Impianto comunale Viterbo","0210069584","Viterbo","Via danimarca","03058","65"),
    ("Centro smaltimento Acacia","0599665854","Alatri","Via dei rovi","03011","21");

INSERT INTO beecological.owner VALUE
	("theManager","aaaaaaaa","Umberto","Fantini","umbyfanty@gmail.com","3330000000","La vecchia mola"),
	("theBoss","bbbbbbbb","Mario","Bettini","marybetty@gmail.com","3331111111","The Trasher"),
    ("theRicilatore","cccccccc","Davide","Rossi","rosdav@yahoo.it","3153669785","Verona pulita"),
	("theOwner","eeeeeeee","Marco","Milito","milito22@libero.it","3311144567","Impianto comunale Viterbo"),
	("theMaster","ffffffff","Gennaro","Verdi","ringhio@yahoo.it","3412697855","Centro smaltimento Acacia");

INSERT INTO beecological.unload(user,center,date,time) VALUE
	("sixpain","La vecchia mola","2020-01-02","15:53"),
    ("sixpain","The Trasher","2020-01-03","18:15"),
    ("stormjack","La vecchia mola","2019-09-02","10:03"),
    ("stormjack","The Trasher","2020-01-21","09:20");
    
INSERT INTO beecological.waste VALUE
	("Bulky waste",1),
	("Glass",1),
	("Mixed material packaging",3),
	("Plastic packaging",3),
	("Metal packaging",2),
	("Toner and cartridges",4),
	("Battery",4),
	("Organic waste",1),
	("Electrical and electronic equipment",2),
	("Cold and climate",4),
	("Wood waste",1),
	("Mercury",5),
	("Textile",1),
	("Tires",3);
    
INSERT INTO beecological.wasteunloaded(user,center,date,time,waste,wastequantity) VALUE
	("sixpain","La vecchia mola","2020-01-02","15:53", "Bulky waste", 10),
    ("sixpain","The Trasher","2020-01-03","18:15", "Mixed material packaging", 50),
    ("stormjack","La vecchia mola","2019-09-02","10:03", "Battery", 221),
    ("stormjack","The Trasher","2020-01-21","09:20", "Wood waste", 100);
    
INSERT INTO beecological.bookingRequest(user,center,date,time,status) VALUE 
	("sixpain","La vecchia mola","2020-01-02","15:53", "R"),
    ("sixpain","The Trasher","2020-01-03","18:15", "R"),
    ("stormjack","La vecchia mola","2019-09-02","10:03", "R"),
    ("stormjack","The Trasher","2020-01-21","09:20", "R"),
	("sixpain","La vecchia mola","2020-03-10","10:20","W"),
    ("sixpain","Verona Pulita","2020-02-08","19:45","A"),
    ("stormjack","The Trasher","2020-09-02","14:00","W"),
    ("stormjack","Impianto comunale Viterbo","2020-01-21","17:45","A");
    
INSERT INTO beecological.item VALUE
	("000","Gift card Amazon 5€",100),
    ("001","Gift card Amazon 10€",200),
    ("002","Gift card Amazon 25€",400),
    ("003","Gift card Zalando 5€",100),
    ("004","Gift card Zalando 10€",190),
    ("005","Gift card Play Store 5€",90),
    ("006","Gift card Play Store 10€",170),
    ("007","Gift card App Store 5€",100),
    ("008","Gift card PSN 5€",100),
    ("010","Gift card Xbox Live 5€",100),
    ("011","Gift card Netflix 10€",200),
    ("012","Gift card AS Roma Store 10€",200),
    ("013","Gift card Microsoft 10€",210),
    ("014","Gift card Feltrinelli 5€",100);