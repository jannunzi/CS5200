using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WA234
{
    public class FormTo
    {
        public int id { set; get; }
        public string name { set; get; }
        public FormTo() { }
        public FormTo(int id, string name)
        {
            this.id = id;
            this.name = name;
        }
    }
}
