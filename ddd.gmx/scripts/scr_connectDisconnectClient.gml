/// Called on a connect or disconnect of a client
{
    // get connect or disconnect (1=connect)
    var t = ds_map_find_value(async_load, "type");

    // Get the NEW socket ID, or the socket that's disconnecting
    var sock = ds_map_find_value(async_load, "socket");
    
    // Get the IP that the socket comes from
    var ip = ds_map_find_value(async_load, "ip");
    
    // Connecting?
    if( t==network_type_connect)
    {
    
        // add client to our list of connected clients
        
        ds_list_add( socketlist, sock );
        global.PlayerTotal++;
        /*     
        instance_create(irandom_range(150, 250),350, obj_circle);
        instance_create(irandom_range(150, 250),375, obj_box);
        */
        
        if(global.PlayerTotal == 1){
            var xx = random_range(120,510);
            var yy = random_range(900,990);
            instance_create(xx,yy,obj_circle);
            
            xx = random_range(120,510);
            yy = random_range(900,990);
            instance_create(xx,yy,obj_box);
            
            global.ChipCount+=2;

        }
        else if (global.PlayerTotal==2){
            var xx = random_range(720,1110);
            var yy = random_range(900,990);
            instance_create(xx,yy,obj_circle);
            
            xx = random_range(720,1110);
            yy = random_range(900,990);
            instance_create(xx,yy,obj_box);
            
            global.ChipCount+=2;
        }
        
        
        //var inst = instance_create(irandom_range(150, 250),350, obj_circle);
    }
    else
    {
        // disconnect a CLIENT. First find the player instance using the socket ID as a lookup
        var inst = ds_map_find_value(Clients, sock );
        with(inst) { instance_destroy(); }
        // Also delete the socket from our global list of connected clients
        var index = ds_list_find_index( socketlist, sock );
        ds_list_delete(socketlist,index);
        global.PlayerTotal--;
        if (global.PlayerTotal < 2){
            global.GameOver = true;
        }
    }
}


