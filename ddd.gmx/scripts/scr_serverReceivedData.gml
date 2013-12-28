/// Read incoming data to the server from a connected saocket
{  
    // get the buffer the data resides in
    var buff = ds_map_find_value(async_load, "buffer");
    
    // read the command 
    var cmd = buffer_read(buff, buffer_s16 );

    // Get the socket ID - this is the CLIENT socket ID. We can use this as a "key" for this client
    var sock = ds_map_find_value(async_load, "id");

    // Is this a KEY command?
    
    if( cmd==KEY_CMD )    
    {
        // Read the key that was sent
        var key = buffer_read(buff, buffer_s16 );

        var updown = buffer_read(buff, buffer_s16 );
        var mousex = buffer_read(buff, buffer_s16 );
        var mousey = buffer_read(buff, buffer_s16 );
        
        
        if( key==mb_left ) {
            //release
            if(updown == 0){
                with(obj_circle){
                    if(var_owner == sock){
                        var_owner = 0;
                        var_left = 0;
                        var_mouse_x = mousex;
                        var_mouse_y = mousey;
                    }
                }
                with(obj_box){
                    if(var_owner == sock){
                        var_owner = 0;
                        var_left = 0;
                        var_mouse_x = mousex;
                        var_mouse_y = mousey;
                    }
                }
            
            }
            //pressed
            else{
            
                with(obj_circle){
                    if(position_meeting(mousex, mousey, self)){
                        var_owner = sock;
                        var_left = 1;
  
                    }
                }
                with(obj_box){
                    if(position_meeting(mousex, mousey, self)){
                        var_owner = sock;
                        var_left = 1;
  
                    }
                }
                
            }
        }
    }

    else if( cmd==PING_CMD )
    {
        // keep alive - ignore it
    }
}


