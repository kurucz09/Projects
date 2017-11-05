using System;
using System.Collections.Specialized;
using System.Net;
using System.Threading.Tasks;
using System.Net.Http;
using System.Text;
using System.Web;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

namespace FacebookApi
{
    public static class FbApi
    {
        public const string ApiVersion = "2.10";
        private const string AppId = "133801767137872";
        private const string AppSecret = "dbb18fe42530292d4d4a8022fc7642bc";
        private const string BaseUrl = "https://graph.facebook.com/";
        private const string VideoBaseUrl = "https://graph-video.facebook.com.";


        private const string AccessToken =
                "EAACEdEose0cBABbg92FIOTsRMXA9eJVlGOKWgK2ZAZBD1MTBsk4kJIVYo4h5me73VOMPzS4ynWFOIdWz6vC8BvBwe0W7MNWMyWhCNPGRvdGTasxqWKJaCjZArjHyLpdRl6mHfQrahcCgeh39GZCp2uvvwjLpkjK8iI4HaqM5bWZCF2tqI2WF2qdOsX5j07kYZD"
            ;

        private static readonly HttpClient HttpClient = new HttpClient();

        public static async Task<ResponseId> PostToPage(string token, string msg)
        {
            var queryParams = new NameValueCollection { };
            JObject payLoad = new JObject(
                new JProperty("message", msg)
            );

            var httpContent = new StringContent(payLoad.ToString(), Encoding.UTF8, "application/json");
            var requestUri = BuildRequestUri("me/feed", queryParams, token);
            return await ExecutePostRequest<ResponseId>(requestUri, httpContent);
        }

        //public static async Task<EventData> GetEvent(string id, string token)
        //{
        //    var queryParams = new NameValueCollection { { "fields", "name,description,start_time,end_time,place,picture" } };
        //    var requestUri = BuildRequestUri(id, queryParams, token);
        //    return await ExecuteGetRequest<EventData>(requestUri);
        //}

        public static async Task<UserData> GetIdAndUserName(string token)
        {
            var queryParams = new NameValueCollection { { "fields", "id, name" } };
            var requestUri = BuildRequestUri("me", queryParams, token);
            return await ExecuteGetRequest<UserData>(requestUri);
        }

        public static async Task<UserInfo> GetUserInfo(string token)
        {
            var queryParams = new NameValueCollection { { "fields", "id,name,email,picture.height(300).width(300),age_range,gender" } };
            var requestUri = BuildRequestUri("me", queryParams, token);
            return await ExecuteGetRequest<UserInfo>(requestUri);

        }

        private static Uri BuildRequestUri(string method, NameValueCollection queryStringParameters, string token)
        {
            var baseUri = new Uri(BaseUrl);
            var uri = new Uri(baseUri, $"/v{ApiVersion}/{method}");
            var uriBuilder = new UriBuilder(uri);

            if (queryStringParameters == null)
                queryStringParameters = new NameValueCollection();
            queryStringParameters.Add("access_token", token);
            var queryStringCollection = HttpUtility.ParseQueryString(uriBuilder.Query);
            queryStringCollection.Add(queryStringParameters);
            uriBuilder.Query = queryStringCollection.ToString();

            return uriBuilder.Uri;
        }

        private static async Task<T> ExecuteGetRequest<T>(Uri uri)
        {
            var requestMessage = new HttpRequestMessage(HttpMethod.Get, uri);

            using (var httpResponseMessage = await HttpClient.SendAsync(requestMessage))
            {
                if (httpResponseMessage.StatusCode != HttpStatusCode.OK)
                {
                    throw new Exception(httpResponseMessage.ReasonPhrase);
                }

                var content = await httpResponseMessage.Content.ReadAsStringAsync();
                if (string.IsNullOrEmpty(content))
                {
                    throw new Exception("Empty content");
                }
                return JsonConvert.DeserializeObject<T>(content);
            }
        }

        private static async Task<T> ExecutePostRequest<T>(Uri uri, HttpContent httpContent)
        {
            var requestMessage = new HttpRequestMessage(HttpMethod.Post, uri);
            //var content = new StringContent();
            requestMessage.Content = httpContent;
            using (var httpResponseMessage = await HttpClient.SendAsync(requestMessage))
            {
                if (httpResponseMessage.StatusCode != HttpStatusCode.OK)
                {
                    throw new Exception(httpResponseMessage.ReasonPhrase);
                }

                var content = await httpResponseMessage.Content.ReadAsStringAsync();
                if (string.IsNullOrEmpty(content))
                {
                    throw new Exception("Empty content");
                }
                return JsonConvert.DeserializeObject<T>(content);
            }
        }
    }
}