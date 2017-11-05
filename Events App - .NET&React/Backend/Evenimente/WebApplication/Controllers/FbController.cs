using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Http;
using FacebookApi;
using FacebookApi = FacebookApi.FbApi;


namespace WebApplication.Controllers
{
    public class FbController : ApiController
    {
        [HttpGet]
        public async Task<UserData> GetIdAndUserName(string id)
        {
            return await FbApi.GetIdAndUserName(id);
        }

        [HttpGet]
        public async Task<UserInfo> GetUserInfo(string id)
        {
            return await FbApi.GetUserInfo(id);
        }


        //[HttpGet]
        //public async Task<EventData> GetEvent(string id, string token)
        //{
        //    return await FacebookApi.FacebookApi.GetEvent(id,token);
        //    //return await FacebookApi.FacebookApi.GetEvent(token, id);
        //}
        //[HttpGet]
        //public async Task<UserBirthday> getBirthday()
        //{
        //    return await FacebookApi.GetBirthday();
        //}


        // jason body { id: token, msg: message to post }
        [HttpPost]
        public async Task<ResponseId> PostToPage([FromBody] dynamic message)
        {
        
            return await FbApi.PostToPage(message.id.ToString(), message.msg.ToString());
        }


    }
}