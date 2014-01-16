The current version of the NewMapBranch sports a cool new Map class which is in charge of rendering
the tiles, actors and grid. It currently renders the tiles and actors but cannot render the grid since
that's not implemented yet.

So if you run the Game.main(), a SuperMapGame will create a new JFrame to interact with. The controls
for this frame are:
    Mouse Wheel : Scroll up     : Zoom in
                : Scroll down   : Zoom out
    Mouse Buttons   : Left click and drag   : Move map
                    : Right click and drag  : Rotate map
                            (only straight up or down applies)

Those are all of the cool new features for the latest change!