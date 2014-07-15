using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace WA123.Controllers
{
    public class FormsController : ApiController
    {
        // GET api/<controller>
        public List<FormTo> Get()
        {
            List<FormTo> formTos = new List<FormTo>();
            using (AM2Entities db = new AM2Entities())
            {
                var forms = from r in db.Forms select r;
                foreach(Form f in forms) {
                    FormTo to = new FormTo { name = f.name, id = f.Id };
                    formTos.Add(to);
                }
            }
            return formTos;
        }

        // GET api/<controller>/5
        public string Get(int id)
        {
            return "value";
        }

        // POST api/<controller>
        public void Post([FromBody]FormTo formTo)
        {
            List<FormControl> controls = new List<FormControl>();
            Form form = new Form();// { name = formTo.name, Id = formTo.id, FormControls = controls };
            form.name = formTo.name;
            using(AM2Entities db = new AM2Entities())
            {
                db.Forms.Add(form);
                db.SaveChanges();
            }
        }

        // PUT api/<controller>/5
        public void Put(int id, [FromBody]FormTo to)
        {
            using (AM2Entities db = new AM2Entities())
            {
                Form form = db.Forms.First(i => i.Id == id);
                form.name = to.name;
                db.SaveChanges();
            }
        }

        // DELETE api/<controller>/5
        public void Delete(int id)
        {
            using (AM2Entities db = new AM2Entities())
            {
                Form form = db.Forms.First(i => i.Id == id);
                db.Forms.Remove(form);
                db.SaveChanges();
            }
        }
    }
}