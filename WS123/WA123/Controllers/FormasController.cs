using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace WA123.Controllers
{
    public class FormasController : ApiController
    {
        // GET api/<controller>
        public List<FormaTo> Get()
        {
            List<FormaTo> formasTo = new List<FormaTo>();

            using(var db = new AM2Entities())
            {
                var formas = from f in db.Forms select f;
                foreach (Form form in formas)
                {
                    FormaTo to = new FormaTo();
                    to.name = form.name;
                    to.id = form.Id;
                    formasTo.Add(to);
                }
            }

            return formasTo;
        }

        // GET api/<controller>/5
        public string Get(int id)
        {
            return "value";
        }

        // POST api/<controller>
        public void Post([FromBody]FormaTo to)
        {
            using (var db = new AM2Entities())
            {
                Form form = new Form();
                form.name = to.name;
                db.Forms.Add(form);
                db.SaveChanges();
            }
        }

        // PUT api/<controller>/5
        public void Put(int id, [FromBody]FormTo value)
        {
            using (var db = new AM2Entities())
            {
                Form form = db.Forms.First(f => f.Id == id);
                form.name = value.name;
                db.SaveChanges();
            }
        }

        // DELETE api/<controller>/5
        public void Delete(int id)
        {
            using (var db = new AM2Entities())
            {
                Form form = db.Forms.First(f => f.Id == id);
                db.Forms.Remove(form);
                db.SaveChanges();
            }
        }
    }
}