var JB = require( "jabber-server" );

JB.Server.prototype.initUserList = function( ) {
    var main_user = new JB.User( "main@localhost", "Main User" );
    return [ main_user ];
}

var server = new JB.Server({ domain: "localhost", port: 5223 });
