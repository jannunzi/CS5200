using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(FormMaker.Startup))]
namespace FormMaker
{
    public partial class Startup {
        public void Configuration(IAppBuilder app) {
            ConfigureAuth(app);
        }
    }
}
