using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace FacebookApi
{
    public class UserInfo
    {
        public string id { get; set; }
        public string name { get; set; }
        public string email { get; set; }
        public Picture picture { get; set; }
        public AgeRange age_range { get; set; }
        public string gender { get; set; }
    }
}