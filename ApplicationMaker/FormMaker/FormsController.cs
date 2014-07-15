using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace FormMaker
{
    public class FormsController : ApiController
    {
        // GET api/<controller>
        public IEnumerable<Form> Get()
        {
            List<Form> forms = new List<Form>();

            using(var db = new FMEntities())
            {
                var fs = from f in db.Forms select f;
                foreach(Form ff in fs)
                {
                    Form form = new Form();
                    form.Id = ff.Id;
                    form.name = ff.name;
                    forms.Add(form);
                }
            }

            return forms;
        }

        // GET api/<controller>/5
        public Form Get(int id)
        {
            Form form = new Form();

            using (var db = new FMEntities())
            {
                var ff = db.Forms.First(f => f.Id == id);
                form.Id = ff.Id;
                form.name = ff.name;
            }

            return form;
        }

        // POST api/<controller>
        public void Post([FromBody]Form newForm)
        {
            using(var db = new FMEntities())
            {
                db.Forms.Add(newForm);
                db.SaveChanges();
            }
        }

        // PUT api/<controller>/5
        public void Put(int id, [FromBody]Form newForm)
        {
            using(var db = new FMEntities())
            {
                var ff = db.Forms.First(f => f.Id == id);
                ff.name = newForm.name;
                db.SaveChanges();
            }
        }

        // DELETE api/<controller>/5
        public void Delete(int id)
        {
            using (var db = new FMEntities())
            {
                var ff = db.Forms.First(f => f.Id == id);
                db.Forms.Remove(ff);
                db.SaveChanges();
            }
        }
    }
}