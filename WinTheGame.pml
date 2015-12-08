#define iSize 100

mtype = { 
	endMessage,
	ranking,
	askName,
	sentName,
	recordTopten,
	showTopten,
}

chan PlayerInterface = [iSize] of { mtype }

proctype Player()
{	
	Start: atomic {
		printf("\n End Game \n");
		
	}
	
	
		
	Next2: atomic {
		printf("\n Player Next2 \n");
		
		goto Start;
	}
	
	Next3: atomic {
		printf("\n Player Next3 \n");
		
	}
}

proctype Interface () 
{
	int n = 0;
	Start: atomic {
		printf("\n Interface start \n");
		
		
	}
	
	Next1: atomic {
		printf("\n Interface Next1 \n");
	}
	
}

init {
	run Player();
	run Interface();
}

