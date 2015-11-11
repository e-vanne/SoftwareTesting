#define iSize 100

mtype = { 
	try,
	result,
	endMessage,
	askIfTop,
	answerTop,
	askStore,
	answerStore,
	askName,
	name,
	storeScore
}

chan PlayerInterface = [iSize] of { mtype }
chan InterfaceScoreFile = [iSize] of { mtype }

proctype Player()
{	
	Start: atomic {
		printf("\n Player start \n");
		PlayerInterface!try;
		goto Next1;
	}
	
	Next1: atomic {
		printf("\n Player Next1 \n");
		if
		:: PlayerInterface?result -> 
			PlayerInterface!try; 
			goto Next1;		
		:: PlayerInterface?endMessage -> 
			goto Next2;
		fi
	}
		
	Next2: atomic {
		printf("\n Player Next2 \n");
		if 
		:: PlayerInterface?askStore ->
			PlayerInterface!answerStore;
			goto Next3;
		fi
		goto Start;
	}
	
	Next3: atomic {
		printf("\n Player Next3 \n");
		PlayerInterface?askName;
		PlayerInterface!name;
		/* goto Start; */
	}
}

proctype Interface () 
{
	int n = 0;
	Start: atomic {
		printf("\n Interface start \n");
		PlayerInterface?try;
		if
		:: (n < 5) -> 
			PlayerInterface!result;
			n = n + 1;
			goto Start;
		:: (n == 5) ->
			PlayerInterface!endMessage; /* Ici : you win ! */
			goto Next1;
		fi
	}
	
	Next1: atomic {
		printf("\n Interface Next1 \n");
		InterfaceScoreFile!askIfTop;
		InterfaceScoreFile?answerTop;
			PlayerInterface!askStore;
		goto Next2;
	}
	
	Next2: atomic {
		printf("\n Interface Next2 \n");
		PlayerInterface?answerStore;
			PlayerInterface!askName;
		goto Next3;
	}
	
	Next3: atomic {
		printf("\n Interface Next3 \n");
		PlayerInterface?name;
			InterfaceScoreFile!storeScore;
		/* goto Start; */
	}
}

proctype ScoreFile () 
{
	Start: atomic {
		printf("\n ScoreFile start \n");
		InterfaceScoreFile?askIfTop;
			InterfaceScoreFile!answerTop;
		goto Next1;
	}
	
	Next1: atomic {
		printf("\n ScoreFile Next1 \n");
		InterfaceScoreFile?storeScore;
			printf("\n store score \n");
		/* goto Start; */
	}
}

init {
	run Player();
	run Interface();
	run ScoreFile();
}

