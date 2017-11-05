using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace FacebookApi
{
    public class Location
    {
        public string city { get; set; }
        public string country { get; set; }
        public double latitude { get; set; }
        public double longitude { get; set; }
        public string street { get; set; }
        public string zip { get; set; }
    }

    public class Place
    {
        public string name { get; set; }
        public Location location { get; set; }
        public string id { get; set; }
    }

    public class DataPicture
    {
        public bool is_silhouette { get; set; }
        public string url { get; set; }
    }

    public class PictureEvent
    {
        public DataPicture data { get; set; }
    }

    public class EventData
    {
        public string name { get; set; }
        public string description { get; set; }
        public string start_time { get; set; }
        public string end_time { get; set; }
        public Place place { get; set; }
        public PictureEvent picture { get; set; }
        public string id { get; set; }
    }
}