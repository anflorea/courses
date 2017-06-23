#include "Participant.h"



Participant::Participant(string _name, int _score) : name(_name), score(_score)
{
}


Participant::~Participant()
{
}

void Participant::addToScore(int nbr) {
	score += nbr;
}
