using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace WebApplication222.Controllers
{
    public class FormsController : ApiController
    {
        // GET api/<controller>
        public List<Form> Get()
        {
            List<Form> forms = new List<Form>();
            Form f1 = new Form { name = "Form 1", Id = 123 };
            Form f2 = new Form { name = "Form 2", Id = 234 };
            forms.Add(f1);
            forms.Add(f2);
            return forms;
        }

        // GET api/<controller>/5
        public string Get(int id)
        {
            return "value";
        }

        // POST api/<controller>
        public void Post([FromBody]string value)
        {
        }

        // PUT api/<controller>/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE api/<controller>/5
        public void Delete(int id)
        {
        }
    }
}