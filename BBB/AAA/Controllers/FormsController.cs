using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace AAA.Controllers
{
    public class FormsController : ApiController
    {
        // GET api/<controller>
        public IEnumerable<Form> Get()
        {
            List<Form> forms = new List<Form>();

            using (var db = new DDD())
            {
                var fs = from f in db.Forms select f;
                foreach (Form ff in fs)
                {
                    Form form = new Form();
                    form.name = ff.name;
                    form.Id = ff.Id;
                    forms.Add(form);
                }
            }

            return forms;
//            return new Form[] { new Form(), new Form() };
        }

        // GET api/<controller>/5
        public Form Get(int id)
        {
            using (var db = new DDD())
            {
                var form = db.Forms.First(f => f.Id == id);
                var ff = new Form();
                ff.name = form.name;
                ff.Id = form.Id;
                return ff;
            }
        }

        // POST api/<controller>
        public void Post([FromBody]Form newForm)
        {
            using (var db = new DDD())
            {
                db.Forms.Add(newForm);
                db.SaveChanges();
            }
        }

        // PUT api/<controller>/5
        public void Put(int id, [FromBody]Form newForm)
        {
            using (var db = new DDD())
            {
                var form = db.Forms.First(f => f.Id == id);
                form.name = newForm.name;
                db.SaveChanges();
            }
        }

        // DELETE api/<controller>/5
        public void Delete(int id)
        {
            using (var db = new DDD())
            {
                var form = db.Forms.First(f => f.Id == id);
                db.Forms.Remove(form);
                db.SaveChanges();
            }
        }
    }
}