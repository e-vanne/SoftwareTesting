#define iSize 100

mtype = { 
	askIfTopten,
	answerTopten,
	newTopTen,
	recordTopten,
	
}

chan PlayerInterface = [iSize] of { mtype }
chan InterfaceScoreFile = [iSize] of { mtype }



proctype Interface () 
{
	int n = 0;
	Start: atomic {
		printf("\n Interface start \n");
		
		
	}
	
	Next1: atomic {
		printf("\n Interface Next1 \n");
		InterfaceScoreFile!askIfTopten;
		goto Next2;
	}
	
	Next2: atomic {
		printf("\n Interface Next2 \n");
		goto Next3;
	}
	
	Next3: atomic {
		printf("\n Interface Next3 \n");
		/* goto Start; */
	}
}

proctype ScoreFile () 
{
	Start: atomic {
		printf("\n ScoreFile start \n");
		InterfaceScoreFile?askIfTopten;
			InterfaceScoreFile!askIfTopten;
		goto Next1;
	}
	
	Next1: atomic {
		printf("\n ScoreFile Next1 \n");
		InterfaceScoreFile?recordTopten;
			printf("\n store score \n");
		/* goto Start; */
	}
}

init {
	run Interface();
	run ScoreFile();
}

