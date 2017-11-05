

export class EventFbServices {

    static API = "http://localhost:60158/api/";
     
    static GetEvent(eventPage : any, id: string, token: string) {
        var myheader = new Headers();
       
        myheader.set('content-type', 'application/json');
        myheader.set('Access-Control-Allow-Origin', '*')
      
        var request = new Request(this.API + "fb/GetEvent?id="+id+"&token="+token, {
            method: "GET",
            headers: myheader
        });

        let response = fetch(request).then(function (response) {
            if (response.ok) {
                return response.json();
            }
            throw new Error('Network response was not ok.');
        })
            .then(function (data) {
                //console.log(data);
            })
            .catch(function (error) {
                // console.log("error");
            })    
    }   

}