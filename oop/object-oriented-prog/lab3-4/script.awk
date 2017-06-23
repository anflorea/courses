#!/bin/awk

{
	for (i = 1; i <= NF; i++) {
		if ($i ~ "(.?)(.?)(.?).?\3\2\1")
				print $i;
		}	
}
