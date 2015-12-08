#define iSize 100

mtype = { 
	try,
	checkWellPlace,
	checkMatchingColor,
	result,
	endMessage
}

chan PlayerInterface = [iSize] of { mtype }

proctype Player()
{	
	Start: atomic {
		printf("\n Player start \n");
		goto Next1;
	}
	
	Next1: atomic {
		PlayerInterface!try;
		//goto Start;
	}
		
	
}

proctype Interface () 
{
	int n = 0;
	int endGame = 0;
	Start: atomic {
		printf("\n Interface start \n");
		PlayerInterface?try;
		if
		:: (n < 5) -> 
			PlayerInterface!checkWellPlace;
			PlayerInterface!checkMatchingColor;
			PlayerInterface!result;
			n = n + 1;
			PlayerInterface?try;
		:: (n == 5) ->
			PlayerInterface!endMessage
			goto Start;
		fi

	}
	
}


init {
	run Player();
	run Interface();
}

