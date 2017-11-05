# myPhysicsEngine
2D physics engine

The goal was to simulate physical 2D motion, with elastic collisions. It is still very incomplete. 

Games oftentimes use code libraries to facilitate interactions between a game's characters/environment, like jumping and landing 
onto a moving platform. 

I was inspired by other projects Jelly Car and Box2D. I thought I could make my own. This project was the start of that,
using my at-the-time knowlege of kenimatics. Eventually, I could make my own games, and other applications, that involve 2D motion.

I also spent a lot of time rehtinking the "design", really intending this code to be a free, simple, 
and easy to use/understand java alternative to the popular Box2D.

I got as far as some basic collision-detection, circular bodies, and concave polygons. Even though it's incomplete, I learned a lot:
A high school level understanding of physics wasn't enough for all I wanted to do, and so I had to do research. 

Other Note: I was having performance issues, when using double-values to store entity state information, e.g. coordinates, orientation.
So, to improve performance, there is a separate set of classes that use int-values for better speed.
