using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace WA234
{
    public class FormsController : ApiController
    {
        // GET api/<controller>
        public List<FormTo> Get()
        {
            List<FormTo> formsTo = new List<FormTo>();
            using (var db = new AM234() )
            {
                var forms = from f in db.Forms select f;
                foreach (Form form in forms)
                {
                    FormTo to = new FormTo(form.Id, form.name);
                    formsTo.Add(to);
                }
            }
            return formsTo;
        }

        // GET api/<controller>/5
        public string Get(int id)
        {
            return "value";
        }

        // POST api/<controller>
        public void Post([FromBody]FormTo to)
        {
            using (var db = new AM234())
            {
                Form form = new Form();
                form.name = to.name;
                db.Forms.Add(form);
                db.SaveChanges();
            }
        }

        // PUT api/<controller>/5
        public void Put(int id, [FromBody]FormTo to)
        {
            using (var db = new AM234())
            {
                Form form = db.Forms.First(f => f.Id == id);
                form.name = to.name;
                db.SaveChanges();
            }
        }

        // DELETE api/<controller>/5
        public void Delete(int id)
        {
            using (var db = new AM234())
            {
                Form form = db.Forms.First(f => f.Id == id);
                db.Forms.Remove(form);
                db.SaveChanges();
            }
        }
    }
}