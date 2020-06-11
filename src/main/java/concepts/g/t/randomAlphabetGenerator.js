
function generateTicket() {
   var result           = '';
   var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
   var charactersLength = characters.length;
   var myarr;
   for ( var i = 0; i < 9; i++ ) {
      var pos = Math.floor(Math.random() * charactersLength);
      if(myarr.contains(characters.charAt(pos))){
         result += characters.charAt(pos));
            myarr.add(characters.charAt(pos));
      }
      }
   }

var div = document.createElement("div");
div.classList.add("list-group");

var button = document.createElement('button');
button.className += "list-group-item list-group-item-action list-group-item-success";
button.innerHTML="Ticket "+(document.getElementsByClassName('list-group').length+1)+".    "+result;

div.appendChild(button);
document.getElementsByClassName('container')[0].appendChild(div);
}
