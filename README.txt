SpeedRun.java

This is a 2d birdTop view racing game.
User moves with W,A,S,D or the arrows on the keyboard.
When the car moves off the track it slows down so try to keep in on the track.
There is unfortunately a server side that doesn't work the user could connect but without a car,
the use joins with his id and cannot really send anything.

Server files – server files create a server and should receive packets from 
clients and update the clients with the data of other cars but sadly the 
server does not allow loading cars at the current state

Client files – receive and send packets to the server and update each client with 
the data sent from other clients. It loads the track picture and should load the other cars 
but unfortunately doesn’t at the current state.


Enjoy the track!