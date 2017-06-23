listens2Music(mia). 
happy(yolanda). 
playsAirGuitar(mia):- 
	listens2Music(mia). 
playsAirGuitar(yolanda):- 
	listens2Music(yolanda). 
listens2Music(yolanda):- 
	happy(yolanda). 
